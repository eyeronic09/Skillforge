package com.eyeronic09.skillforge.HomeScreen.data.remote.mapper

import com.eyeronic09.skillforge.HomeScreen.domain.Model.CourseError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.SerializationException
import java.io.IOException

fun errorMapeper(e: Throwable) : CourseError{
    return when(e){
        is ClientRequestException -> {
            when(e.response.status.value){
                401 -> CourseError.Unauthorized
                404 -> CourseError.NotFound
                else -> {
                    CourseError.Unknown(e.message ?: "Client Request Error")
                }
            }
        }
        is ServerResponseException -> {
            CourseError.Server
        }
        is IOException -> {
            CourseError.Network
        }
        is SerializationException -> {
            CourseError.Unknown("Serialization Error: ${e.message}")
        }
        else -> {
            CourseError.Unknown(e.message ?: "Unknown Error")
        }
    }
}