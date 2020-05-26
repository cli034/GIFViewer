package com.example.gifviewer.data.service

import com.example.gifviewer.domain.model.TenorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("trending")
    suspend fun retrieveTrendingGifs(
        @Query("pos") position: String,
        @Query("key") api_key: String
    ): TenorResponse
}