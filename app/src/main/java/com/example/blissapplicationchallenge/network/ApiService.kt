package com.example.blissapplicationchallenge.network

import com.example.blissapplicationchallenge.network.response.AvatarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("users/{username}")
    suspend fun getAvatar(
        @Path("username") username: String
    ): Response<AvatarResponse>
}