package com.example.gifviewer.domain.repository

import com.example.gifviewer.data.ApiConstants
import com.example.gifviewer.data.service.GifService
import com.example.gifviewer.domain.model.TenorResponse
import javax.inject.Inject

class GifRepoImpl @Inject constructor(
    private val gifService: GifService
) : GifRepo {

    override suspend fun retrieveTrendingGifs(nextQueryPos: String): TenorResponse {
        return gifService.retrieveTrendingGifs(nextQueryPos, ApiConstants.API_KEY)
    }

    override suspend fun retrieveSearchGifs(keyword: String, nextQueryPos: String): TenorResponse {
        return gifService.retrieveSearchGifs(keyword, nextQueryPos, ApiConstants.API_KEY)
    }
}