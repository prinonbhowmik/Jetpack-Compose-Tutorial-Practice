package com.example.retrofitjp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class MainViewModel {
    private val apiService = RetrofitInstance.api
    val posts: MutableState<List<Post>> = mutableStateOf(emptyList())
    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getPosts()
                if (response.isNotEmpty()) {
                    posts.value = response
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}