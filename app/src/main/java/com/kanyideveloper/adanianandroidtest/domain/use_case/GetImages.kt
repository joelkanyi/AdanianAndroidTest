package com.kanyideveloper.adanianandroidtest.domain.use_case

import com.kanyideveloper.adanianandroidtest.data.repository.ImageRepository
import com.kanyideveloper.adanianandroidtest.domain.model.Image
import com.kanyideveloper.adanianandroidtest.util.Resource
import kotlinx.coroutines.flow.Flow

class GetImages(
    private val repository: ImageRepository
) {

    operator fun invoke(name: String): Flow<Resource<List<Image>>>{
        return repository.getImages(name)
    }
}