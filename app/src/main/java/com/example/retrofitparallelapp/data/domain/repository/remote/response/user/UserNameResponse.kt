package com.example.retrofitparallelapp.data.domain.repository.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserNameResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int
)
