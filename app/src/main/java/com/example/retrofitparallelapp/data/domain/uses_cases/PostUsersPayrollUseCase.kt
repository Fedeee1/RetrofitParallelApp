package com.example.retrofitparallelapp.data.domain.uses_cases

import com.example.retrofitparallelapp.data.domain.UsersRepository
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollRequest
import javax.inject.Inject

class PostUsersPayrollUseCase @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(user: UserPayrollRequest):BaseResponse<UserPayrollModel> {
        return repository.postUsersPayroll(user)
    }
}