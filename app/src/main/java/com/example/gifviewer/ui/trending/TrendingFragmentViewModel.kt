package com.example.gifviewer.ui.trending

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifviewer.domain.repository.GifRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrendingFragmentViewModel @Inject constructor(
    private val gifRepo: GifRepo
) : ViewModel() {

    init {
        getTrendingGifs()
    }

    private fun getTrendingGifs() {
        viewModelScope.launch {
            try {
                val response = gifRepo.retrieveTrendingGifs()
            } catch (throwable: Throwable) {
                onTrendingGifsError(throwable)
            }
        }
    }

    private fun onTrendingGifsError(throwable: Throwable) {
        Log.d("error", throwable.toString())
    }
}