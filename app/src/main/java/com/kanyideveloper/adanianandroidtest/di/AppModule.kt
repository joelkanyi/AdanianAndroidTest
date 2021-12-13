package com.kanyideveloper.adanianandroidtest.di

import android.app.Application
import androidx.room.Room
import com.kanyideveloper.adanianandroidtest.data.local.ImageDao
import com.kanyideveloper.adanianandroidtest.data.local.ImageDatabase
import com.kanyideveloper.adanianandroidtest.data.remote.PixabayApi
import com.kanyideveloper.adanianandroidtest.data.repository.ImageRepository
import com.kanyideveloper.adanianandroidtest.data.util.Constants.BASE_URL
import com.kanyideveloper.adanianandroidtest.domain.repository.ImageRepositoryImpl
import com.kanyideveloper.adanianandroidtest.domain.use_case.GetImages
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetImagesUseCase(repository: ImageRepository): GetImages {
        return GetImages(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun provideImagesRepository(
        db: ImageDatabase,
        pixabayApi: PixabayApi

    ): ImageRepository {
        return ImageRepositoryImpl(
            pixabayApi = pixabayApi,
            imageDao = db.dao
        )
    }

    @Provides
    @Singleton
    fun provideImageDatabase(application: Application): ImageDatabase {
        return Room.databaseBuilder(
            application,
            ImageDatabase::class.java,
            "images_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PixabayApi::class.java)
    }
}