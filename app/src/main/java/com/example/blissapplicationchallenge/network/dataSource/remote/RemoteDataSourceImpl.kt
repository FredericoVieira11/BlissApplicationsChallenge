package com.example.blissapplicationchallenge.network.dataSource.remote

import com.example.blissapplicationchallenge.network.ApiService
import com.example.blissapplicationchallenge.network.response.AvatarResponse
import com.example.blissapplicationchallenge.network.response.EmojiResponse
import com.example.blissapplicationchallenge.network.response.GoogleRepoResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {

    override suspend fun getEmojis(): List<EmojiResponse> {
        val test = apiService.getEmojis()
        return test.map {
            EmojiResponse(it.key, it.value)
        }.toList()
    }

    override suspend fun getAvatar(
        avatar: String
    ): Response<AvatarResponse> = this.apiService.getAvatar(username = avatar)

    override suspend fun getGoogleRepos(
        username: String,
        page: Int,
        size: Int
    ): Response<List<GoogleRepoResponse>> = this.apiService.getGoogleRepos(username, page, size)


}