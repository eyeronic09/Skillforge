package com.eyeronic09.skillforge.HomeScreen.data.remote.mapper

sealed class Result <out T , out E>{
    data class Success<out T>(val data : T) : Result<T , Nothing>()
    data class Error<out E>(val errorMessage : E) : Result<Nothing , E>()
}