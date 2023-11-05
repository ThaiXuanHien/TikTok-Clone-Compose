package com.hienthai.tiktok_clone.ui.video.composeables

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hienthai.tiktok_clone.R
import com.hienthai.tiktok_clone.ui.theme.TikTokCloneTheme

@Composable
fun AvatarView(
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier.clickable { onClick() }) {
        val (imageAvatar, addIcon) = createRefs()
        Image(painter = painterResource(id = R.drawable.ic_dog),
            contentDescription = "Icon Avatar",
            modifier = modifier
                .size(48.dp)
                .background(shape = CircleShape, color = Color.White)
                .border(width = 2.dp, shape = CircleShape, color = Color.White)
                .clip(shape = CircleShape)
                .constrainAs(imageAvatar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        Box(
            modifier = modifier
                .size(24.dp)
                .background(shape = CircleShape, color = MaterialTheme.colors.error)
                .constrainAs(addIcon) {
                    top.linkTo(imageAvatar.bottom)
                    bottom.linkTo(imageAvatar.bottom)
                    start.linkTo(imageAvatar.start)
                    end.linkTo(imageAvatar.end)
                }, contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Icon Add",
                tint = Color.White,
                modifier = modifier.size(12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun AvatarViewPreview() {
    TikTokCloneTheme {
        AvatarView {

        }
    }
}

@Composable
fun AudioTrackView(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotate by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = InfiniteRepeatableSpec(
            repeatMode = RepeatMode.Restart, animation = tween(
                durationMillis = 5000, easing = LinearEasing
            )
        ), label = ""
    )
    Image(
        painter = painterResource(id = R.drawable.ic_audio_track),
        contentDescription = "Audio Track",
        modifier = modifier
            .size(30.dp)
            .rotate(rotate)
    )
}

@Preview
@Composable
fun AudioTrackViewPreview() {
    TikTokCloneTheme {
        AudioTrackView()
    }
}

@Composable
fun VideoAttractiveInfoItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            tint = Color.White,
            modifier = modifier.size(30.dp)
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(text = text, style = MaterialTheme.typography.body2.copy(color = Color.White))
    }
}

@Preview
@Composable
fun VideoAttractiveInfoItemPreView() {
    TikTokCloneTheme {
        VideoAttractiveInfoItem(icon = R.drawable.ic_heart, text = "1.5M") {

        }
    }
}

@Composable
fun SideBarView(
    modifier: Modifier = Modifier,
    onAvatarClick: () -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onSaveClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AvatarView {

        }
        Spacer(modifier = modifier.height(16.dp))
        VideoAttractiveInfoItem(icon = R.drawable.ic_heart, text = "1.5M") {
            onLikeClick()
        }
        Spacer(modifier = modifier.height(16.dp))
        VideoAttractiveInfoItem(icon = R.drawable.ic_chat, text = "1.5M") {
            onCommentClick()
        }
        Spacer(modifier = modifier.height(16.dp))
        VideoAttractiveInfoItem(icon = R.drawable.ic_bookmark, text = "1.5M") {
            onSaveClick()
        }
        Spacer(modifier = modifier.height(16.dp))
        VideoAttractiveInfoItem(icon = R.drawable.ic_share, text = "1.5M") {
            onShareClick()
        }
        Spacer(modifier = modifier.height(16.dp))
        AudioTrackView()
    }
}

@Preview
@Composable
fun SideBarViewPreview() {
    TikTokCloneTheme {
        SideBarView(
            onAvatarClick = { /*TODO*/ },
            onLikeClick = { /*TODO*/ },
            onCommentClick = { /*TODO*/ },
            onSaveClick = { /*TODO*/ }) {
        }
    }
}