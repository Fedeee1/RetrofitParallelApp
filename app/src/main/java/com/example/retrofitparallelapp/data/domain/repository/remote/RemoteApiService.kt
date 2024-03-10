package com.example.retrofitparallelapp.data.domain.repository.remote

import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.ListNamesResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserJobResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollRequest
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSalaryResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSurnameResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteApiService {

    @GET("names")
    suspend fun getListNames(
    ): Response<ListNamesResponse>

    @GET("surname/{id}")
    suspend fun getSurname(
        @Path("id") id: Int
    ): Response<UserSurnameResponse>

    @GET("job/{id}")
    suspend fun getJob(
        @Path("id") id: Int
    ): Response<UserJobResponse>

    @GET("salary/{id}")
    suspend fun getSalary(
        @Path("id") id: Int
    ): Response<UserSalaryResponse>

    @POST("payroll")
    suspend fun postPayroll(
        @Body post: UserPayrollRequest
    ): Response<UserPayrollResponse>

    @GET("secuence/{id}")
    suspend fun getSecuence(
        @Path("id") id: Int
    ): Response<UserJobResponse>

}