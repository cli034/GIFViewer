package com.example.gifviewer.ui.gif

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifviewer.domain.model.GifObject
import com.example.gifviewer.domain.repository.GifRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifFragmentViewModel @Inject constructor(
    private val gifRepo: GifRepo
) : ViewModel() {

    private val _gifObjectList = MutableLiveData<List<GifObject>>()
    val gifObjectList: LiveData<List<GifObject>> = _gifObjectList

    private val _trendingNextQueryPos = MutableLiveData<String>()
    val trendingNextQueryPos: LiveData<String> = _trendingNextQueryPos

    private val _searchNextQueryPos = MutableLiveData<String>()
    val searchNextQueryPos: LiveData<String> = _searchNextQueryPos

    var currentKeyword = ""

    init {
        getTrendingGifs()
    }

    private fun getTrendingGifs() {
        viewModelScope.launch {
            try {
                val response = gifRepo.retrieveTrendingGifs(nextQueryPos = trendingNextQueryPos.value ?: "")
                response.next?.let {
                    _trendingNextQueryPos.value = it
                }
                response.results?.let {
                    _gifObjectList.value = processPagination(it)
                }
            } catch (throwable: Throwable) {
                onTrendingGifsError(throwable)
            }
        }
    }

    private fun onTrendingGifsError(throwable: Throwable) {
        Log.d("error", throwable.toString())
    }

    fun searchGifs() {
        viewModelScope.launch {
            try {
                val response = gifRepo.retrieveSearchGifs(
                    keyword = currentKeyword,
                    nextQueryPos = searchNextQueryPos.value ?: ""
                )
                response.next?.let {
                    _searchNextQueryPos.value = it
                }
                response.results?.let {
                    _gifObjectList.value = processPagination(it)
                }
            } catch (throwable: Throwable) {
                onSearchGifsError(throwable)
            }
        }
    }

    private fun onSearchGifsError(throwable: Throwable) {
        Log.d("error", throwable.toString())
    }

    fun updateSearchKeywordAndReset(keyword: String) {
        _gifObjectList.value = null
        _searchNextQueryPos.value = null
        currentKeyword = keyword
    }

    fun loadMoreGifs() {
        if (currentKeyword.isEmpty()) {
            getTrendingGifs()
        } else {
            searchGifs()
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
}