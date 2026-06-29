package com.eyeronic09.skillforge.HomeScreen.data.remote.mapper

sealed class NetworkResult <out T , out E>{
    data class Success<out T>(val data : T) : NetworkResult<T , Nothing>()
    data class Error<out E>(val errorMessage : E) : NetworkResult<Nothing , E>()
}