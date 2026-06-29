package com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos

import kotlinx.serialization.Serializable

@Serializable
data class InstructorDto(
    val id: String,
    val bio: String,
    val name: String,
    val avatarUrl: String,
    val title: String
)
