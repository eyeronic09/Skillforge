package com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id : String,
    val name : String,
    val description : String,
    val iconColor: String ,
    val courseCount : Int,
    val courses : List<CourseDto>
)