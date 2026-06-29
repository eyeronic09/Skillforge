package com.eyeronic09.skillforge.HomeScreen.domain.Reposistory

import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.ResponseDto
import com.eyeronic09.skillforge.HomeScreen.data.remote.mapper.NetworkResult
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Category
import com.eyeronic09.skillforge.HomeScreen.domain.Model.CourseError

interface CourseRepository {
    suspend fun getCourses(): NetworkResult<List<Category>, CourseError>
}
