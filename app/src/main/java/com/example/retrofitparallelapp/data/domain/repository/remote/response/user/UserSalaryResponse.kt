package com.example.retrofitparallelapp.data.domain.repository.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserSalaryResponse(
    @SerializedName("salary")
    val salary: String,
    @SerializedName("tax")
    val tax: Double,
    @SerializedName("formation")
    val formation: Double
)
