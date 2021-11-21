package com.example.blissapplicationchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.blissapplicationchallenge.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "my_db"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: AppDataBase) = db.recipeDao()
}