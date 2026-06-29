package com.eyeronic09.skillforge.HomeScreen.data.remote.api

import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.ResponseDto

interface CourseApi {
    suspend fun getCourses(): ResponseDto
}
