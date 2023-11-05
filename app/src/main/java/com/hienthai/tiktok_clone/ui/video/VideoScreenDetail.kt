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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.hienthai.tiktok_clone.designsystem.TikTokVideoPlayer
import com.hienthai.tiktok_clone.ui.video.composeables.SideBarView
import com.hienthai.tiktok_clone.ui.video.composeables.VideoInfoArea

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
        val (videoPlayer, sideBar, videoInfoArea) = createRefs()
        TikTokVideoPlayer(player = player, modifier = Modifier.constrainAs(videoPlayer) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.matchParent
        })

        SideBarView(
            onAvatarClick = {},
            onLikeClick = {},
            onCommentClick = {},
            onSaveClick = {},
            onShareClick = {}, modifier = Modifier.constrainAs(sideBar) {
                end.linkTo(parent.end, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
            })

        VideoInfoArea(
            accountName = "Khame Pet",
            videoName = "Funny Pets",
            hashTags = listOf("#pet", "#funny"),
            songName = "rock in roll",
            modifier = Modifier.constrainAs(videoInfoArea) {
                start.linkTo(parent.start, margin = 8.dp)
                bottom.linkTo(sideBar.bottom)
                end.linkTo(sideBar.start, margin = 32.dp)
                width = Dimension.fillToConstraints
            })

    }
}