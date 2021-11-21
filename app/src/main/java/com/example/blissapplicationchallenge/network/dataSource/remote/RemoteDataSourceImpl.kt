package com.example.blissapplicationchallenge.network.dataSource.remote

import com.example.blissapplicationchallenge.network.ApiService
import com.example.blissapplicationchallenge.network.response.EmojiResponse
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

}