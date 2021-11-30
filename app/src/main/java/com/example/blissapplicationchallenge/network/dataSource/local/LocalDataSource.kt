package com.example.blissapplicationchallenge.network.dataSource.local

import com.example.blissapplicationchallenge.room.AvatarEntity
import com.example.blissapplicationchallenge.room.EmojiEntity

interface LocalDataSource {
    fun getList(): List<EmojiEntity>
    suspend fun setEmojiList(emojiList: List<EmojiEntity>?)

    fun getAvatarList(): List<AvatarEntity>
    suspend fun setAvatar(avatarEntity: AvatarEntity)

    fun deleteAvatar(avatar: AvatarEntity)
}