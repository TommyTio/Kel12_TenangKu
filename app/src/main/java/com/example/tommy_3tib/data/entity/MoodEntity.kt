package com.example.tommy_3tib.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class MoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val emoji: String,
    val moodTitle: String,
    val moodLabel: String,
    val note: String,
    val tags: String,
    val suggestion: String,
    val createdAt: Long = System.currentTimeMillis()
)
