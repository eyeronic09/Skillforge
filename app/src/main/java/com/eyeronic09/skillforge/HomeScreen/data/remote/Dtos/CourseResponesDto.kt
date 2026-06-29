package com.eyeronic09.skillforge.HomeScreen.data.remote.Dtos

import kotlinx.serialization.Serializable
@Serializable
data class ResponseDto(
    val meta: MetaDto,
    val categories: List<CategoryDto>
)

@Serializable
data class MetaDto(
    val app: String,
    val version: String,
    val generatedAt: String
)
