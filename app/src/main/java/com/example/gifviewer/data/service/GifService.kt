package com.example.gifviewer.data.service

import com.example.gifviewer.domain.model.TensorResponse
import retrofit2.http.GET

interface GifService {

    @GET("trending?key=XC9G48L86JRK")
    suspend fun retrieveTrendingGifs(): TensorResponse
}