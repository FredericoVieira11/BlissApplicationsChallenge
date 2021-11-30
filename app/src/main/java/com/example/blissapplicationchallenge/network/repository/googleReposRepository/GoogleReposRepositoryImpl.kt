package com.example.blissapplicationchallenge.network.repository.googleReposRepository

import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSource
import com.example.blissapplicationchallenge.network.model.GoogleRepoModel
import javax.inject.Inject

class GoogleReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): GoogleReposRepository {

    override suspend fun getGoogleReposList(
        username: String,
        page: Int,
        size: Int
    ): List<GoogleRepoModel> {
        val list = this.remoteDataSource.getGoogleRepos(
            username,
            page,
            size
        )
        return list.body()?.map {
            GoogleRepoModel(it.name)
        }!!.toList()
    }

}