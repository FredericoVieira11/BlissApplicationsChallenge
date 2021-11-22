package com.example.blissapplicationchallenge.network.dataSource.remote

import com.example.blissapplicationchallenge.network.response.EmojiResponse

interface RemoteDataSource {
    suspend fun getEmojis(): List<EmojiResponse>
}