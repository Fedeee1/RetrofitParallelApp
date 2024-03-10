package com.example.retrofitparallelapp.data.domain.uses_cases

import com.example.retrofitparallelapp.data.domain.UsersRepository
import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersJobUseCase @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(id: Int): BaseResponse<UserJobModel> {
        return repository.getUsersJob(id)
    }
}