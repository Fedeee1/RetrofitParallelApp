package com.example.retrofitparallelapp.data.domain.model.user

import com.example.retrofitparallelapp.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserJobModel(
    val job: String,
    val company: String
) : BaseModel()
