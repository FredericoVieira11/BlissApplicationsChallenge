package com.example.blissapplicationchallenge.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmojiEntity::class, AvatarEntity::class], version = 2)
abstract class AppDataBase: RoomDatabase() {
    abstract fun recipeDao(): DataBaseDao
}