package com.example.gifviewer.domain.model

data class GifObject(
    val id: String,
    val media: List<GifFormat>
)