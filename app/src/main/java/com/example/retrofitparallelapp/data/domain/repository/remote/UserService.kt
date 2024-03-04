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

    fun getUsersSurname(id: Int): Flow<BaseResponse<UserSurnameModel>> = flow {
        val apiResult = apiCallService.getUsersSurname(id)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(UserSurnameMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    fun getUsersJob(id: Int): Flow<BaseResponse<UserJobModel>> = flow {
        val apiResult = apiCallService.getUsersJob(id)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(UserJobMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    fun getUsersSalary(id: Int): Flow<BaseResponse<UserSalaryModel>> = flow {
        val apiResult = apiCallService.getUsersSalary(id)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(UserSalaryMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    fun postUsersPayroll(user: UserPayrollModel): Flow<BaseResponse<UserPayrollModel>> = flow {
        val apiResult = apiCallService.postUsersPayroll(user)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(UserPayrollMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }
}