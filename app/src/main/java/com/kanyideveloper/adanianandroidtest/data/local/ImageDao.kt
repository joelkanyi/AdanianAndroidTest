package com.kanyideveloper.adanianandroidtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanyideveloper.adanianandroidtest.domain.model.Image

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageEntity>)

    @Query("DELETE FROM imageentity WHERE previewURL IN(:images)")
    suspend fun deleteImages(images: List<String>)

    @Query("SELECT * FROM imageentity")
    suspend fun getImages(name: String?): List<ImageEntity>
}