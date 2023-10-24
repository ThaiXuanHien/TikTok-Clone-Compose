package com.hienthai.tiktok_clone.data.repository

import com.hienthai.tiktok_clone.R
import javax.inject.Inject

class VideoRepository @Inject constructor() {
    private val videoList = listOf(
        R.raw.test, R.raw.test2, R.raw.test3, R.raw.test4
    )

    fun getVideo() = videoList.random()
}