package com.example.blissapplicationchallenge.network.repository

import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSource
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSource
import com.example.blissapplicationchallenge.network.response.EmojiResponse
import com.example.blissapplicationchallenge.room.EmojiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomEmojisRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): RandomEmojisRepository{

    override suspend fun getEmojis(): List<EmojiResponse> {
        //TODO: 1º verificar se os dados tao vazios na BD; 2º se tiver vazio, pega do RemoteDataSource e salva senao pegar do LocalDataSource
        return if (this.localDataSource.getList().isEmpty()) {
            saveData()
            getList()
        } else {
            getList()
        }
    }

    private suspend fun saveData() = withContext(Dispatchers.IO) {
        async {
            val list = remoteDataSource.getEmojis()
            val emojiEntity = list.map {
                EmojiEntity(id = null, name = it.name, url = it.url)
            }.toList()
            localDataSource.setEmojiList(emojiEntity)
        }
    }.await()

    private fun getList(): List<EmojiResponse> {
        val list = this.localDataSource.getList()
        return list.map {
            EmojiResponse(it.name!!, it.url!!)
        }.toList()
    }

}