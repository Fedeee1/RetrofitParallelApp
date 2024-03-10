package com.example.retrofitparallelapp.data.domain

import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.UserService
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserJobResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: UserService) {

    fun getListUsers(): Flow<BaseResponse<ListNamesModel>> {
        return api.getListUsers()
    }
    suspend fun getUsersSurname(id: Int): BaseResponse<UserSurnameModel> {
        return api.getUsersSurname(id)
    }
    suspend fun getUsersJob(id: Int): BaseResponse<UserJobModel> {
        return api.getUsersJob(id)
    }
    suspend fun getUsersSalary(id: Int): BaseResponse<UserSalaryModel> {
        return api.getUsersSalary(id)
    }

    suspend fun postUsersPayroll(user: UserPayrollRequest): BaseResponse<UserPayrollModel> {
        return api.postUsersPayroll(user)
    }
}

