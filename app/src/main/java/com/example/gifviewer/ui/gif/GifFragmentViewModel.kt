package com.example.gifviewer.ui.gif

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifviewer.domain.model.GifObject
import com.example.gifviewer.domain.model.TenorResponse
import com.example.gifviewer.domain.repository.GifRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifFragmentViewModel @Inject constructor(
    private val gifRepo: GifRepo
) : ViewModel() {

    private val _gifObjectList = MutableLiveData<List<GifObject>>()
    val gifObjectList: LiveData<List<GifObject>> = _gifObjectList

    private val _nextQueryPos = MutableLiveData<String>()
    val nextQueryPos: LiveData<String> = _nextQueryPos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    var currentKeyword = ""

    init {
        getTrendingGifs()
    }

    private fun getTrendingGifs() {
        showProgressBar()
        viewModelScope.launch {
            try {
                val response = gifRepo.retrieveTrendingGifs(nextQueryPos = nextQueryPos.value ?: "")
                onResponseSuccess(response)
                hideProgressBar()
            } catch (throwable: Throwable) {
                onTrendingGifsError(throwable)
            }
        }
    }

    private fun onTrendingGifsError(throwable: Throwable) {
        hideProgressBar()
        Log.d("error", throwable.toString())
    }

    fun searchGifs() {
        showProgressBar()
        viewModelScope.launch {
            try {
                val response = gifRepo.retrieveSearchGifs(
                    keyword = currentKeyword,
                    nextQueryPos = nextQueryPos.value ?: ""
                )
                onResponseSuccess(response)
                hideProgressBar()
            } catch (throwable: Throwable) {
                onSearchGifsError(throwable)
            }
        }
    }

    private fun onSearchGifsError(throwable: Throwable) {
        hideProgressBar()
        Log.d("error", throwable.toString())
    }

    private fun onResponseSuccess(response: TenorResponse) {
        response.next?.let {
            _nextQueryPos.value = it
        }
        response.results?.let {
            _gifObjectList.value = processPagination(it)
        }
    }

    private fun processPagination(responseList: List<GifObject>): List<GifObject> {
        return _gifObjectList.value?.let {
            mutableListOf<GifObject>().apply {
                addAll(it)
                addAll(responseList)
            }
        } ?: responseList
    }

    fun updateSearchKeyword(keyword: String) {
        resetCurrentData()
        currentKeyword = keyword
    }

    fun loadMoreGifs() {
        if (currentKeyword.isEmpty()) {
            getTrendingGifs()
        } else {
            searchGifs()
        }
    }

    fun showTrendingGifs() {
        resetCurrentData()
        getTrendingGifs()
    }

    private fun resetCurrentData() {
        currentKeyword = ""
        _gifObjectList.value = null
        _nextQueryPos.value = null
    }

    private fun showProgressBar() {
        _isLoading.value = true
    }

    private fun hideProgressBar() {
        _isLoading.value = false
    }
}