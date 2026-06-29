package com.eyeronic09.skillforge.HomeScreen.domain.Reposistory

import com.eyeronic09.skillforge.HomeScreen.domain.Model.Category

interface CourseRepository {
    suspend fun getCourses(): Result<List<Category>>
}
