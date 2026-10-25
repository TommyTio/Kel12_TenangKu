package com.example.tommy_3tib

data class MoodModel(
    val emoji: String,
    val moodTitle: String,
    val dateTime: String,
    val moodLabel: String,
    val note: String,
    val tags: List<String>,
    val suggestion: String
)