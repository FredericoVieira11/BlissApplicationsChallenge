package com.example.blissapplicationchallenge.network.repository

import com.example.blissapplicationchallenge.network.response.EmojiResponse

interface RandomEmojisRepository {
    suspend fun getEmojis(): List<EmojiResponse>
}