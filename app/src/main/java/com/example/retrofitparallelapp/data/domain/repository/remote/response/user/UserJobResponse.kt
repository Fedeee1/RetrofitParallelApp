package com.example.retrofitparallelapp.data.domain.repository.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserJobResponse(
    @SerializedName("job")
    val job: String,
    @SerializedName("company")
    val company: String
)
