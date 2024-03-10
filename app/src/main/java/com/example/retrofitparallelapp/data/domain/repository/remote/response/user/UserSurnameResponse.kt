package com.example.retrofitparallelapp.data.domain.repository.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserSurnameResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String
)