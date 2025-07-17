package com.example.vendingmachinejp.screens.videoItems.view

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.vendingmachinejp.R
import com.example.vendingmachinejp.navigation.Screen
import com.example.vendingmachinejp.screens.videoItems.model.ReelsVideoData

@Composable
fun AutoPlayVideoLazyRow(videoUrls: List<ReelsVideoData>, navController: NavController) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(currentIndex) {
        listState.animateScrollToItem(currentIndex)
    }

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = false

    ) {
        itemsIndexed(videoUrls) { index, url ->
            Box(
                modifier = Modifier
                    .fillParentMaxSize()
                    .clickable{
                        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.old_radio_button_click_97549)
                        mp.start()
                        navController.navigate(Screen.home.route)
                    }
            ) {
                VideoPlayerItem(
                    url = url.mediaLink.toString(),
                    play = index == currentIndex,
                    onVideoEnded = {
                        currentIndex = if (currentIndex == videoUrls.lastIndex) 0 else currentIndex + 1
                    }
                )
            }
        }
    }


}
