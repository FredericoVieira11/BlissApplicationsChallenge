package com.example.blissapplicationchallenge.network.response

import com.google.gson.annotations.SerializedName

data class AvatarResponse (
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String
)