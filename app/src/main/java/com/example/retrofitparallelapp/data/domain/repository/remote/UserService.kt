package com.example.retrofitparallelapp.data.domain.repository.remote

import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.list_users_mapper.ListUsersMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_job_mapper.UserJobMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_payroll_mapper.UserPayrollMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_salary_mapper.UserSalaryMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_surname_mapper.UserSurnameMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollRequest
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

    suspend fun getUsersSurname(id: Int): BaseResponse<UserSurnameModel> {
        return when (val apiResult = apiCallService.getUsersSurname(id)) {
            is BaseResponse.Success  -> {
               BaseResponse.Success(UserSurnameMapper().fromResponse(apiResult.data))
            }
            is BaseResponse.Error -> {
               BaseResponse.Error(apiResult.error)
            }
        }

    }

    suspend fun getUsersJob(id: Int): BaseResponse<UserJobModel> {
        return when (val apiResult = apiCallService.getUsersJob(id)) {
            is BaseResponse.Success  -> {
                BaseResponse.Success(UserJobMapper().fromResponse(apiResult.data))
            }
            is BaseResponse.Error -> {
                BaseResponse.Error(apiResult.error)
            }
        }
    }

    suspend fun getUsersSalary(id: Int):BaseResponse<UserSalaryModel> {
        return when (val apiResult = apiCallService.getUsersSalary(id)) {
            is BaseResponse.Success  -> {
                BaseResponse.Success(UserSalaryMapper().fromResponse(apiResult.data))
            }
            is BaseResponse.Error -> {
                BaseResponse.Error(apiResult.error)
            }
        }
    }

    suspend fun postUsersPayroll(user: UserPayrollRequest):BaseResponse<UserPayrollModel> {
        return when ( val apiResult = apiCallService.postUsersPayroll(user)) {
            is BaseResponse.Success  -> {
                BaseResponse.Success(UserPayrollMapper().fromResponse(apiResult.data))
            }
            is BaseResponse.Error -> {
                BaseResponse.Error(apiResult.error)
            }
        }
    }
}