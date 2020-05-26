package com.example.gifviewer.domain.repository

import com.example.gifviewer.data.service.GifService
import com.example.gifviewer.domain.model.TensorResponse
import javax.inject.Inject

class GifRepoImpl @Inject constructor(
    private val gifService: GifService
) : GifRepo {

    override suspend fun retrieveTrendingGifs(): TensorResponse {
        return gifService.retrieveTrendingGifs()
    }
}