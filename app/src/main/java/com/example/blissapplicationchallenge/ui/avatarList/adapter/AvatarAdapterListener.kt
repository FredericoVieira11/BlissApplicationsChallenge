package com.example.blissapplicationchallenge.ui.avatarList.adapter

import com.example.blissapplicationchallenge.network.model.AvatarModel

interface AvatarAdapterListener {
    fun onDeleteAvatar(avatar: AvatarModel)
}