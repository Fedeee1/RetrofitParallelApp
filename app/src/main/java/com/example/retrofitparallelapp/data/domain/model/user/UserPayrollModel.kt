package com.example.retrofitparallelapp.data.domain.model.user

import com.example.retrofitparallelapp.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPayrollModel(
    val name: String,
    val surname: String,
    val job: String,
    val salary : Double,
    val total: Double
) : BaseModel()
