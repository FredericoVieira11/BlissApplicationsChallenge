package com.example.blissapplicationchallenge.network.repository

import com.example.blissapplicationchallenge.network.model.EmojiModel

interface RandomEmojisRepository {
    suspend fun getEmojis(): List<EmojiModel>
}