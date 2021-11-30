package com.example.blissapplicationchallenge.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avatar")
data class AvatarEntity (
    @ColumnInfo(name = "login")
    var login: String?,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String?
)