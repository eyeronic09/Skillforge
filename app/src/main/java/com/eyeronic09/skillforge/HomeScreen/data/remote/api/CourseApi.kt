package com.eyeronic09.skillforge.HomeScreen.data.remote.api

sealed interface CourseApi {
    suspend fun getCourseApi () : Any
}