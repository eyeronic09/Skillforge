package com.eyeronic09.skillforge.HomeScreen.data.remote.api

import android.util.Log
import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.ResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class CourseApiImpl(private val ktorClient : HttpClient) : CourseApi {
    override suspend fun getCourses(): ResponseDto {
        val response: HttpResponse = ktorClient.get("data.json")
        Log.d("CourseApiImpl", "Response Body: ${response.bodyAsText()}")
        return response.body()
    }
}
