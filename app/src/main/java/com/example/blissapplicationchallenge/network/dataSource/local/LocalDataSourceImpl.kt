package com.example.blissapplicationchallenge.network.dataSource.local

import com.example.blissapplicationchallenge.room.EmojiDao
import com.example.blissapplicationchallenge.room.EmojiEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val emojiDao: EmojiDao
): LocalDataSource {
    override fun getList(): List<EmojiEntity> = emojiDao.getEmojiList()
    override suspend fun setEmojiList(emojiList: List<EmojiEntity>) = emojiDao.setEmojiList(emojiEntityList = emojiList)
}