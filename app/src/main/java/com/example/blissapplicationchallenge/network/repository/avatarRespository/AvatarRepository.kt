package com.example.blissapplicationchallenge.network.repository.avatarRespository

import com.example.blissapplicationchallenge.network.model.AvatarModel

interface AvatarRepository {
    suspend fun getAvatar(avatar: String): AvatarModel?

    fun getAvatarList(): List<AvatarModel>

    fun deleteAvatar(avatar: AvatarModel)
}