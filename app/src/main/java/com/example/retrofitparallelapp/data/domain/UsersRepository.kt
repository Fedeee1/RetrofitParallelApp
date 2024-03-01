package com.example.retrofitparallelapp.data.domain

import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.repository.remote.UserService
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: UserService) {

    fun getListUsers(): Flow<BaseResponse<ListNamesModel>> {
        return api.getListUsers()
    }

}

