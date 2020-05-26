package com.example.gifviewer.domain.repository

import com.example.gifviewer.domain.model.TensorResponse

interface GifRepo {

    suspend fun retrieveTrendingGifs(): TensorResponse
}