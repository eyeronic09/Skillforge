package com.eyeronic09.skillforge.HomeScreen.data.remote.ReposistoryImpl

import android.util.Log
import com.eyeronic09.skillforge.HomeScreen.data.remote.api.CourseApi
import com.eyeronic09.skillforge.HomeScreen.data.remote.mapper.NetworkResult
import com.eyeronic09.skillforge.HomeScreen.data.remote.mapper.errorMapeper
import com.eyeronic09.skillforge.HomeScreen.data.remote.mapper.toDomain
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Category
import com.eyeronic09.skillforge.HomeScreen.domain.Model.CourseError
import com.eyeronic09.skillforge.HomeScreen.domain.Reposistory.CourseRepository

class CourseReposistoryImpl(
    private val courseApi: CourseApi
) : CourseRepository {
    override suspend fun getCourses(): NetworkResult<List<Category> , CourseError> {
        return try {
            val response = courseApi.getCourses()
            val category = response.categories.map { it.toDomain() }
            NetworkResult.Success(category)
        }catch (e : Exception) {
            Log.e("CourseRepository", "Error getting courses", e)
            NetworkResult.Error(errorMessage = errorMapeper(e))
        }
    }


}