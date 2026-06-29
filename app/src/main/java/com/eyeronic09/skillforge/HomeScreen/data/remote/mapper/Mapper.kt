package com.eyeronic09.skillforge.HomeScreen.data.remote.mapper

import androidx.compose.ui.text.font.lerp
import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.CategoryDto
import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.CourseDto
import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.InstructorDto
import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.LessonDto
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Category
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Course
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Instructor
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Lesson

fun CategoryDto.toDomain() : Category{
    return Category(
        id = id,
        name = name,
        description = description,
        iconColor = iconColor,
        courseCount = courseCount,
        courses = courses.map { courseDto -> courseDto.toDomain()},
    )
}

fun CourseDto.toDomain() : Course {
    return Course(
        id = id,
        title = title,
        subtitle = subtitle,
        thumbnailUrl = thumbnailUrl,
        level = level,
        durationHours = durationHours,
        rating = rating,
        studentsEnrolled = studentsEnrolled,
        language = language,
        lastUpdated = lastUpdated,
        tags = tags,
        instructor = instructorDto.toDomain(),
        description = description,
        lessons = lessonDto.map { it.toDomain() }
    )
}

fun InstructorDto.toDomain() : Instructor{
    return Instructor(
        id = id,
        name = name,
        title = title,
        avatarUrl = avatarUrl,
        bio = bio
    )
}

fun LessonDto.toDomain(): Lesson {
    return Lesson(
        id = id,
        title = title,
        durationMinutes = durationMinutes,
        isFree = isFree,
        videoUrl = videoUrl,
        content = content
    )
}