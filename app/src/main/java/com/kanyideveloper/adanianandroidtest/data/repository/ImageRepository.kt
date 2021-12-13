package com.kanyideveloper.adanianandroidtest.data.repository

import com.kanyideveloper.adanianandroidtest.domain.model.Image
import com.kanyideveloper.adanianandroidtest.util.Resource
import kotlinx.coroutines.flow.Flow


interface ImageRepository {

    fun getImages(name: String? = "dogs"): Flow<Resource<List<Image>>>

}