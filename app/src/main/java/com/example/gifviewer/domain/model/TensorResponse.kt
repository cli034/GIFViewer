package com.example.gifviewer.domain.model

data class TensorResponse (
    var next: String? = null,
    var results: List<GifObject>? = null,
    var error: String? = null
)