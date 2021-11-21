package com.example.blissapplicationchallenge.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmojiEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun recipeDao(): EmojiDao
}