package com.example.blissapplicationchallenge.network.repository.avatarRespository

import com.example.blissapplicationchallenge.network.model.AvatarModel
import com.example.blissapplicationchallenge.network.response.AvatarResponse
import retrofit2.Response

interface AvatarRepository {
    suspend fun getAvatar(avatar: String): AvatarModel
    fun getAvatarList(): List<AvatarModel>
}