package com.kanyideveloper.adanianandroidtest.domain.use_case

import com.kanyideveloper.adanianandroidtest.data.repository.FakeImagesRepository
import org.junit.Before

class GetImagesTest{

    private lateinit var getImages: GetImages
    private lateinit var fakeImagesRepository: FakeImagesRepository

    @Before
    fun setup(){
        fakeImagesRepository = FakeImagesRepository()
        getImages = GetImages(fakeImagesRepository)
    }

}