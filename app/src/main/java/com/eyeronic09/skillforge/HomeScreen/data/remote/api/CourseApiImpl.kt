package com.eyeronic09.skillforge.HomeScreen.data.remote.api

import android.util.Log
import com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos.ResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class CourseApiImpl(private val ktorClient : HttpClient) : CourseApi {
    private val baseUrl = "https://raw.githubusercontent.com/android-assesment/notes/refs/heads/main/"
    override suspend fun getCourses(): ResponseDto {
        return try {
            val response = ktorClient.get(urlString = "$baseUrl/data.json")
            response.body()
        } catch (e: Exception){
            throw e
        }
    }
}
