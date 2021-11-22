package com.example.blissapplicationchallenge.network.dataSource.local

import com.example.blissapplicationchallenge.room.EmojiEntity

interface LocalDataSource {
    fun getList(): List<EmojiEntity>

    suspend fun setEmojiList(emojiList: List<EmojiEntity>)
}