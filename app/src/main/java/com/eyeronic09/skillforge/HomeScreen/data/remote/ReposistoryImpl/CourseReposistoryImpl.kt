package com.eyeronic09.skillforge.HomeScreen.data.remote.ReposistoryImpl

import com.eyeronic09.skillforge.HomeScreen.data.remote.api.CourseApi
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Category
import com.eyeronic09.skillforge.HomeScreen.domain.Reposistory.CourseRepository

class CourseReposistoryImpl(
    private val courseApi: CourseApi
) : CourseRepository {
    override suspend fun getCourses(): Result<List<Category>> {

    }

}