package com.example.retrofitjp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun PostList(viewModel: MainViewModel){
    val posts by viewModel.posts.collectAsState()
    LazyColumn {
        items(posts) {
                post ->
            Text(text = post.title)
        }
    }
    DisposableEffect(Unit) {
        viewModel.getPosts()
        onDispose {}
    }
}