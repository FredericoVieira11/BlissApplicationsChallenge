package com.example.blissapplicationchallenge.di

import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSource
import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSourceImpl
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSource
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSourceImpl
import com.example.blissapplicationchallenge.network.repository.EmojisRepository
import com.example.blissapplicationchallenge.network.repository.EmojisRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Binds
    abstract fun bindRandomEmojisRepository(repository: EmojisRepositoryImpl): EmojisRepository

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}