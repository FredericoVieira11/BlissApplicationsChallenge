package com.example.blissapplicationchallenge.room

import androidx.room.*

@Dao
interface DataBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setEmojiList(emojiEntityList: List<EmojiEntity>)

    @Query("SELECT * FROM emoji ")
    fun getEmojiList(): List<EmojiEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAvatar(avatar: AvatarEntity)

    @Query("SELECT * FROM avatar ")
    fun getAvatarList(): List<AvatarEntity>

    @Delete
    fun deleteAvatar(avatar: AvatarEntity)

}