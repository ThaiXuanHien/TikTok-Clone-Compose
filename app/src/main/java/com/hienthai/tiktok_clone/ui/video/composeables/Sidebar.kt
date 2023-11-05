package com.hienthai.tiktok_clone.ui.video.composeables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hienthai.tiktok_clone.R
import com.hienthai.tiktok_clone.ui.theme.TikTokCloneTheme

@Composable
fun AvatarView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier.clickable { onClick() }) {
        val (imageAvatar, addIcon) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_dog),
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
                }
        )
        Box(
            modifier = modifier
                .size(24.dp)
                .background(shape = CircleShape, color = MaterialTheme.colors.error)
                .constrainAs(imageAvatar) {
                    top.linkTo(imageAvatar.top)
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