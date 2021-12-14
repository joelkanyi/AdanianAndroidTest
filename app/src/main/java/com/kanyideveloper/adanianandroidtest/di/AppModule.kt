package com.kanyideveloper.adanianandroidtest.di

import android.app.Application
import androidx.room.Room
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
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
    fun providePixabayApi(okHttpClient: OkHttpClient): PixabayApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(PixabayApi::class.java)
    }
}