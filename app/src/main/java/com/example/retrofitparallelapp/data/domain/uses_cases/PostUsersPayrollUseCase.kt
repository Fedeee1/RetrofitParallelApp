package com.example.retrofitparallelapp.data.domain.uses_cases

import com.example.retrofitparallelapp.data.domain.UsersRepository
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUsersPayrollUseCase @Inject constructor(private val repository: UsersRepository){
    operator fun invoke(user: UserPayrollModel): Flow<BaseResponse<UserPayrollModel>> {
        return repository.postUsersPayroll(user)
    }
}