package com.example.blissapplicationchallenge.ui.avatarList.adapter

import com.example.blissapplicationchallenge.network.model.AvatarModel

interface IAvatarAdapterListener {
    fun onDeleteAvatar(avatar: AvatarModel)
}