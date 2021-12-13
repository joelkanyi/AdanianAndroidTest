package com.kanyideveloper.adanianandroidtest.data.remote.dto

import com.kanyideveloper.adanianandroidtest.domain.model.Image

data class ImageDto(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userId: Int,
    val userImageURL: String,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
){

    // Mapper function to map the data to our model
    fun toImage(): Image {
         return Image(
             collections = collections,
             comments = comments,
             downloads = downloads,
             id = id,
             imageHeight = imageHeight,
             imageSize = imageSize,
             imageWidth = imageWidth,
             largeImageURL = largeImageURL,
             likes = likes,
             pageURL = pageURL,
             previewHeight = previewHeight,
             previewURL = previewURL,
             previewWidth = previewWidth,
             tags = tags,
             type = type,
             user = user,
             userId = userId,
             userImageURL = userImageURL,
             views = views,
             webformatHeight = webformatHeight,
             webformatURL = webformatURL,
             webformatWidth = webformatWidth
         )
    }
}