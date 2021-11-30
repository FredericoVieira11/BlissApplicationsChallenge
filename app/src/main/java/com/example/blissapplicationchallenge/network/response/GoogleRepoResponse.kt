package com.example.blissapplicationchallenge.network.response

import com.google.gson.annotations.SerializedName

data class GoogleRepoResponse (
    @SerializedName("full_name")
    val name: String
)