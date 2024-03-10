package com.example.retrofitparallelapp.data.domain.model.user

import com.example.retrofitparallelapp.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserSalaryModel(
    val salary: String,
    val tax: Double,
    val formation: Double
) : BaseModel()
