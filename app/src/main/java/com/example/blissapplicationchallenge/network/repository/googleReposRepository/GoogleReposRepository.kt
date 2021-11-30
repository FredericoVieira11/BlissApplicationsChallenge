package com.example.blissapplicationchallenge.network.repository.googleReposRepository

import com.example.blissapplicationchallenge.network.model.GoogleRepoModel

interface GoogleReposRepository {
    suspend fun getGoogleReposList(
        username: String,
        page: Int,
        size: Int
    ): List<GoogleRepoModel>
}