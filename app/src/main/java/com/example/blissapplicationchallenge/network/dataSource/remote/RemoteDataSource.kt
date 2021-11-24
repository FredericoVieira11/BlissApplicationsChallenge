package com.example.blissapplicationchallenge.network.dataSource.remote

import com.example.blissapplicationchallenge.network.response.AvatarResponse
import com.example.blissapplicationchallenge.network.response.EmojiResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getEmojis(): List<EmojiResponse>

    suspend fun getAvatar(
        avatar: String
    ): Response<AvatarResponse>
}