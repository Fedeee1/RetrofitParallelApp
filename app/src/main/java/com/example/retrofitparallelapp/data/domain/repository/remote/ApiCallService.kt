package com.example.retrofitparallelapp.data.domain.repository.remote

import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseApiCallService
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.ListNamesResponse
import com.example.retrofitparallelapp.di.InjectionSingleton.Companion.provideApiServices
import javax.inject.Inject

class ApiCallService @Inject constructor() : BaseApiCallService() {

    suspend fun getListUsers(): BaseResponse<ListNamesResponse> {
        return apiCall {
            provideApiServices().getListNames()
        }
    }
}