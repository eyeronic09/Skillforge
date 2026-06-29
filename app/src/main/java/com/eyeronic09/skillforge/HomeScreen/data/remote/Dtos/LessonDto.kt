package com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos

import kotlinx.serialization.Serializable

@Serializable
data class LessonDto(
    val id: String,
    val videoUrl: String,
    val title: String,
    val durationMinutes: Int,
    val isFree: Boolean,
    val content: String
)