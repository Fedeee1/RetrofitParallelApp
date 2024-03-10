package com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_payroll_mapper

import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollResponse

class UserPayrollMapper : ResponseMapper<UserPayrollResponse, UserPayrollModel> {
    override fun fromResponse(response: UserPayrollResponse): UserPayrollModel {
        return UserPayrollModel(response.name, response.surname, response.job, response.salary, response.total)
    }
}