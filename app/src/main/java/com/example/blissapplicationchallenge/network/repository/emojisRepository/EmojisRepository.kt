package com.example.blissapplicationchallenge.network.repository.emojisRepository

import com.example.blissapplicationchallenge.network.model.EmojiModel

interface EmojisRepository {
    suspend fun getEmojis(): List<EmojiModel>
}