package com.example.retrofitparallelapp.data.domain.repository.remote

import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseApiCallService
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.ListNamesResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserJobResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollRequest
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSalaryResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSurnameResponse
import com.example.retrofitparallelapp.di.InjectionSingleton.Companion.provideApiServices
import javax.inject.Inject

class ApiCallService @Inject constructor() : BaseApiCallService() {

    suspend fun getListUsers(): BaseResponse<ListNamesResponse> {
        return apiCall {
            provideApiServices().getListNames()
        }
    }

   suspend fun getUsersSurname(id: Int): BaseResponse<UserSurnameResponse> {
        return apiCall {
            provideApiServices().getSurname(id)
        }
    }

    suspend fun getUsersJob(id: Int): BaseResponse<UserJobResponse> {
        return apiCall {
            provideApiServices().getJob(id)
        }
    }

    suspend fun getUsersSalary(id: Int): BaseResponse<UserSalaryResponse> {
        return apiCall {
            provideApiServices().getSalary(id)
        }
    }

    suspend fun postUsersPayroll(userPayrollRequest: UserPayrollRequest): BaseResponse<UserPayrollResponse> {
        return apiCall {
            provideApiServices().postPayroll(userPayrollRequest)
        }
    }
}