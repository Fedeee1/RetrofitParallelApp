package com.example.retrofitparallelapp.data.domain.repository.remote.response.user

import com.google.gson.annotations.SerializedName

data class ListNamesResponse(
    @SerializedName("names")
    val names: List<UserNameResponse>
)