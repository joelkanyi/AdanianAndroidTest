package com.kanyideveloper.adanianandroidtest.presentation.images

import com.kanyideveloper.adanianandroidtest.domain.model.Image

data class ImagesState(
    val images: List<Image> = emptyList(),
    val isLoading: Boolean = false
)
