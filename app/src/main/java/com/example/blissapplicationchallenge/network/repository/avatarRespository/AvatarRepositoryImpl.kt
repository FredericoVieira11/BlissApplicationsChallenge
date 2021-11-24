package com.example.blissapplicationchallenge.network.repository.avatarRespository

import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSource
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSource
import com.example.blissapplicationchallenge.network.model.AvatarModel
import com.example.blissapplicationchallenge.network.response.AvatarResponse
import com.example.blissapplicationchallenge.room.AvatarEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AvatarRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): AvatarRepository {
    override suspend fun getAvatar(avatar: String): AvatarModel {
        val test = this.remoteDataSource.getAvatar(avatar = avatar)
        saveData(avatar)
        return AvatarModel(test.body()!!.login, test.body()!!.id, test.body()!!.avatarUrl)
    }

    private suspend fun saveData(avatar: String) = withContext(Dispatchers.IO) {
        async {
            val avatarResponse = remoteDataSource.getAvatar(avatar)
            val avatarEntity = AvatarEntity(
                avatarResponse.body()?.login,
                avatarResponse.body()?.id,
                avatarResponse.body()?.avatarUrl
            )
            localDataSource.setAvatar(avatarEntity)
        }
    }.await()

    override fun getAvatarList(): List<AvatarModel> {
        val list = this.localDataSource.getAvatarList()
        return list.map {
            AvatarModel(
                it.login!!,
                it.id!!,
                it.avatarUrl!!
            )
        }.toList()
    }

}