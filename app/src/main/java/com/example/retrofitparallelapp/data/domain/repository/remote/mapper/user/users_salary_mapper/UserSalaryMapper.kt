package com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_salary_mapper

import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserJobResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSalaryResponse

class UserSalaryMapper : ResponseMapper<UserSalaryResponse, UserSalaryModel> {
    override fun fromResponse(response: UserSalaryResponse): UserSalaryModel {
        return UserSalaryModel(response.salary, response.tax, response.formation)
    }
}