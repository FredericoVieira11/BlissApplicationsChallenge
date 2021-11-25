package com.example.blissapplicationchallenge.network.dataSource.local

import com.example.blissapplicationchallenge.room.AvatarEntity
import com.example.blissapplicationchallenge.room.DataBaseDao
import com.example.blissapplicationchallenge.room.EmojiEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataBaseDao: DataBaseDao
): LocalDataSource {
    override fun getList(): List<EmojiEntity> = this.dataBaseDao.getEmojiList()
    override suspend fun setEmojiList(emojiList: List<EmojiEntity>) = this.dataBaseDao.setEmojiList(emojiEntityList = emojiList)

    override fun getAvatarList(): List<AvatarEntity> = this.dataBaseDao.getAvatarList()
    override suspend fun setAvatar(avatarEntity: AvatarEntity) = this.dataBaseDao.setAvatar(avatarEntity)

    override fun deleteAvatar(avatar: AvatarEntity) = this.dataBaseDao.deleteAvatar(avatar)

}