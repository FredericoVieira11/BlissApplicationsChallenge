package com.example.blissapplicationchallenge.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmojiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setEmojiList(emojiEntityList: List<EmojiEntity>)

    @Query("SELECT * FROM emoji ")
    fun getEmojiList(): List<EmojiEntity>
}