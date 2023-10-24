package com.hienthai.tiktok_clone.ui.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.hienthai.tiktok_clone.designsystem.TikTokVideoPlayer

@UnstableApi
@Composable
fun VideoDetailScreen(
    videoId: Int,
    viewModel: VideoDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    if (uiState.value == VideoDetailUiState.Default) {
        viewModel.handleAction(VideoDetailAction.LoadData(videoId))
    }

    VideoDetailScreen(
        uiState = uiState.value,
        player = viewModel.videoPlayer
    ) { videoDetailAction ->
        viewModel.handleAction(videoDetailAction)
    }

}

@UnstableApi
@Composable
fun VideoDetailScreen(
    uiState: VideoDetailUiState,
    player: Player, handleActionVideo: (VideoDetailAction) -> Unit
) {
    when (uiState) {
        is VideoDetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading ...")
            }
        }

        is VideoDetailUiState.Success -> {
            VideoDetailScreen(player = player, handleActionVideo)
        }

        else -> {

        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@UnstableApi
@Composable
fun VideoDetailScreen(
    player: Player,
    handleActionVideo: (VideoDetailAction) -> Unit
) {

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .clickable(onClick = {
            handleActionVideo(VideoDetailAction.ToggleVideo)
        }
        )) {
        val (videoPlayer, sideBar) = createRefs()
        TikTokVideoPlayer(player = player, modifier = Modifier.constrainAs(videoPlayer) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.matchParent
        })
    }
}