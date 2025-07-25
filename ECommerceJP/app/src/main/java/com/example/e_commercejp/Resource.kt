package com.example.e_commercejp

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>: Resource<T>()
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String): Resource<T>(null, message)
}