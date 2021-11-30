package com.example.blissapplicationchallenge.network

import com.example.blissapplicationchallenge.network.response.AvatarResponse
import com.example.blissapplicationchallenge.network.response.GoogleRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("users/{username}")
    suspend fun getAvatar(
        @Path("username") username: String
    ): Response<AvatarResponse>

    @GET("users/{username}/repos")
    suspend fun getGoogleRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): Response<List<GoogleRepoResponse>>
}