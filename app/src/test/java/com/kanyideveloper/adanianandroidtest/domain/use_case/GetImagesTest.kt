package com.kanyideveloper.adanianandroidtest.domain.use_case

import com.kanyideveloper.adanianandroidtest.data.repository.FakeImagesRepository
import com.kanyideveloper.adanianandroidtest.domain.repository.ImageRepositoryImpl
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetImagesTest{

    private lateinit var getImages: GetImages
    private lateinit var fakeImagesRepository: FakeImagesRepository

    @Before
    fun setup(){
        fakeImagesRepository = FakeImagesRepository()
        getImages = GetImages(fakeImagesRepository)
    }

}