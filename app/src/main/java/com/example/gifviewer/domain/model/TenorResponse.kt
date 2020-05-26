package com.example.gifviewer.domain.model

data class TenorResponse (
    var next: String? = null,
    var results: List<GifObject>? = null,
    var error: String? = null
)