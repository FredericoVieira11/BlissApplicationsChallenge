package com.example.blissapplicationchallenge.di

import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSource
import com.example.blissapplicationchallenge.network.dataSource.local.LocalDataSourceImpl
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSource
import com.example.blissapplicationchallenge.network.dataSource.remote.RemoteDataSourceImpl
import com.example.blissapplicationchallenge.network.repository.emojisRepository.EmojisRepositoryImpl
import com.example.blissapplicationchallenge.network.repository.emojisRepository.EmojisRepository
import com.example.blissapplicationchallenge.network.repository.avatarRespository.AvatarRepository
import com.example.blissapplicationchallenge.network.repository.avatarRespository.AvatarRepositoryImpl
import com.example.blissapplicationchallenge.network.repository.googleReposRepository.GoogleReposRepository
import com.example.blissapplicationchallenge.network.repository.googleReposRepository.GoogleReposRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindEmojisRepository(repository: EmojisRepositoryImpl): EmojisRepository

    @Binds
    abstract fun bindAvatarRepository(repository: AvatarRepositoryImpl): AvatarRepository

    @Binds
    abstract fun bindGoogleReposRepository(repository: GoogleReposRepositoryImpl): GoogleReposRepository
}