package com.example.tommy_3tib.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tommy_3tib.data.entity.MoodEntity

@Dao
interface MoodDao {

    @Query("SELECT * FROM moods ORDER BY createdAt DESC")
    suspend fun getAll(): List<MoodEntity>

    @Query("SELECT * FROM moods WHERE id = :id")
    suspend fun getById(id: Int): MoodEntity?

    @Query("SELECT COUNT(*) FROM moods")
    suspend fun count(): Int

    @Insert
    suspend fun insert(mood: MoodEntity)

    @Insert
    suspend fun insertAll(moods: List<MoodEntity>)

    @Delete
    suspend fun delete(mood: MoodEntity)
}
