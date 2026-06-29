package com.eyeronic09.skillforge.HomeScreen.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyeronic09.skillforge.HomeScreen.data.remote.mapper.NetworkResult
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Category
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Course
import com.eyeronic09.skillforge.HomeScreen.domain.Model.CourseError
import com.eyeronic09.skillforge.HomeScreen.domain.Reposistory.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeScreenUiState(
    val error: String? = null,
    val loading : Boolean = false,
    val category:  List<Category> = emptyList(),
    val popularCourses: List<Course> = emptyList()
)
class HomeScreenVM(
    private val repository: CourseRepository
) : ViewModel() {
    private val _UiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState())
    val uiState : StateFlow<HomeScreenUiState> = _UiState.asStateFlow()

    init {
       getCourse()
    }

    private fun getCourse() {
        viewModelScope.launch {
            _UiState.update {
                it.copy(loading = true , error = null)
            }
            when(val result = repository.getCourses()){
                is NetworkResult.Error -> {
                    Log.e("HomeScreenVM", "Error fetching courses: ${result.errorMessage}")
                    val displayError = when(result.errorMessage) {
                        is CourseError.Network -> "Network Error: Please check your internet connection"
                        is CourseError.Server -> "Server Error: Something went wrong on our end"
                        is CourseError.Unauthorized -> "Unauthorized: Please log in again"
                        is CourseError.NotFound -> "Error: Data not found"
                        else -> "An unexpected error occurred: ${result.errorMessage}"
                    }
                    _UiState.update {
                        it.copy(
                            loading = false,
                            error = displayError
                        )
                    }
                }
                is NetworkResult.Success -> {
                    val allCourses = result.data.flatMap { it.courses }
                    _UiState.update {
                        it.copy(
                            loading = false,
                            category = result.data,
                            popularCourses = allCourses,
                            error = null
                        )
                    }
                }
            }
        }
    }

}