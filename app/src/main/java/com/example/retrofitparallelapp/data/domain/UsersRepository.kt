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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: UserService) {

    fun getListUsers(): Flow<BaseResponse<ListNamesModel>> {
        return api.getListUsers()
    }
    fun getUsersSurname(id: Int): Flow<BaseResponse<UserSurnameModel>> {
        return api.getUsersSurname(id)
    }
    fun getUsersJob(id: Int): Flow<BaseResponse<UserJobModel>> {
        return api.getUsersJob(id)
    }
    fun getUsersSalary(id: Int): Flow<BaseResponse<UserSalaryModel>> {
        return api.getUsersSalary(id)
    }

    fun postUsersPayroll(user: UserPayrollModel): Flow<BaseResponse<UserPayrollModel>> {
        return api.postUsersPayroll(user)
    }
}

