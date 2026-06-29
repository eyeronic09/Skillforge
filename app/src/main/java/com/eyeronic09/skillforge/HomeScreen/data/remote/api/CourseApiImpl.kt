package com.eyeronic09.skillforge.HomeScreen.data.remote.api

import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.ResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CourseApiImpl(private val ktorClient : HttpClient) : CourseApi {
    override suspend fun getCourses(): ResponseDto {
        return ktorClient.get(urlString = "data.json").body()
    }
}
