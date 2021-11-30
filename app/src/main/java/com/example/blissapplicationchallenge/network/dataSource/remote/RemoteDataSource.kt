package com.example.blissapplicationchallenge.network.dataSource.remote

import com.example.blissapplicationchallenge.network.response.AvatarResponse
import com.example.blissapplicationchallenge.network.response.EmojiResponse
import com.example.blissapplicationchallenge.network.response.GoogleRepoResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getEmojis(): List<EmojiResponse>?

    suspend fun getAvatar(
        avatar: String
    ): Response<AvatarResponse>

    suspend fun getGoogleRepos(
        username: String,
        page: Int,
        size: Int
    ): Response<List<GoogleRepoResponse>>
}