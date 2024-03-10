package com.example.retrofitparallelapp.data.domain.uses_cases

import com.example.retrofitparallelapp.data.domain.UsersRepository
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import javax.inject.Inject

class GetUsersSalaryUseCase @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(id: Int): BaseResponse<UserSalaryModel> {
        return repository.getUsersSalary(id)
    }
}