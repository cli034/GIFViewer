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

    private val _nextQueryPos = MutableLiveData<String>()
    val nextQueryPos: LiveData<String> = _nextQueryPos

    init {
        getTrendingGifs()
    }

    private fun getTrendingGifs() {
        viewModelScope.launch {
            try {
                val response = gifRepo.retrieveTrendingGifs(nextQueryPos = nextQueryPos.value ?: "")
                response.next?.let {
                    _nextQueryPos.value = it
                }
                response.results?.let {
                    _gifObjectList.value = it
                }
            } catch (throwable: Throwable) {
                onTrendingGifsError(throwable)
            }
        }
    }

    private fun onTrendingGifsError(throwable: Throwable) {
        Log.d("error", throwable.toString())
    }
}