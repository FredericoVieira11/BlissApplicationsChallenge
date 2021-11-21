package com.example.blissapplicationchallenge.network

import retrofit2.http.GET

interface ApiService {
    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>
}