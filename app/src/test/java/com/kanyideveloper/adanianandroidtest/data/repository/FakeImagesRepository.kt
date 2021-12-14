package com.kanyideveloper.adanianandroidtest.data.repository

import com.kanyideveloper.adanianandroidtest.domain.model.Image
import com.kanyideveloper.adanianandroidtest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeImagesRepository : ImageRepository  {

    private val images = mutableListOf<Image>()

    override fun getImages(name: String?): Flow<Resource<List<Image>>> {
        return flow {
            images
        }
    }
}