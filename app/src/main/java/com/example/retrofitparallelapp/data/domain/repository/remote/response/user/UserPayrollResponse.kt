package com.example.retrofitparallelapp.data.domain.repository.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserPayrollResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("salary")
    val salary : Double,
    @SerializedName("total")
    val total: Double
)
