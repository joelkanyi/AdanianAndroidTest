package com.kanyideveloper.adanianandroidtest.domain.repository

import com.kanyideveloper.adanianandroidtest.data.local.ImageDao
import com.kanyideveloper.adanianandroidtest.data.remote.PixabayApi
import com.kanyideveloper.adanianandroidtest.data.repository.ImageRepository
import com.kanyideveloper.adanianandroidtest.domain.model.Image
import com.kanyideveloper.adanianandroidtest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ImageRepositoryImpl(
    private val pixabayApi: PixabayApi,
    private val imageDao: ImageDao
) : ImageRepository {

    override fun getImages(name: String?): Flow<Resource<List<Image>>> = flow {

        emit(Resource.Loading())

        // Read the data in our cache
        val images = imageDao.getImages(name).map { it.toImage() }
        emit(Resource.Loading(data = images))

        try {

            // Get our words anc replace them in the database
            val remoteImages = pixabayApi.searchImages(name)
            imageDao.deleteImages(remoteImages.hits.map { it.previewURL })
            imageDao.insertImages(remoteImages.hits.map { it.toImageEntity() })

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = images
                )
            )

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection!",
                    data = images
                )
            )

        }

        // Emit our data to the UI
        val newImages = imageDao.getImages(name).map { it.toImage() }
        emit(Resource.Success(newImages))
    }
}