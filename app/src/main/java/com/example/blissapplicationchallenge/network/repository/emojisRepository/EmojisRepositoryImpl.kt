package com.example.blissapplicationchallenge.network.repository.emojisRepository

import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSource
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSource
import com.example.blissapplicationchallenge.network.model.EmojiModel
import com.example.blissapplicationchallenge.room.EmojiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmojisRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): EmojisRepository{

    override suspend fun getEmojis(): List<EmojiModel> {
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
            val emojiEntity = list?.map {
                EmojiEntity(id = null, name = it.name, url = it.url)
            }?.toList()
            localDataSource.setEmojiList(emojiEntity)
        }
    }.await()

    private fun getList(): List<EmojiModel> {
        val list = this.localDataSource.getList()
        return list.map {
            EmojiModel(it.name!!, it.url!!)
        }.toList()
    }

}