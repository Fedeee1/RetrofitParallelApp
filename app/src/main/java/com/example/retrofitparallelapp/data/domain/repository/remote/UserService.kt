package com.example.retrofitparallelapp.data.domain.repository.remote

import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.ListUsersMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserService @Inject constructor(private val apiCallService: ApiCallService) {

    fun getListUsers(): Flow<BaseResponse<ListNamesModel>> = flow {
        val apiResult = apiCallService.getListUsers()
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(ListUsersMapper().fromResponse(apiResult.data.names)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }
}