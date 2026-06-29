package com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CourseDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val thumbnailUrl: String,
    val level: String,
    val durationHours: Double,
    val rating: Double,
    val studentsEnrolled: Int,
    val language: String,
    val lastUpdated: String,
    val tags: List<String>,
    val instructorDto: InstructorDto,
    val descriptionDto: DescrpitionDto,
    val lessonDto: List<LessonDto>
)