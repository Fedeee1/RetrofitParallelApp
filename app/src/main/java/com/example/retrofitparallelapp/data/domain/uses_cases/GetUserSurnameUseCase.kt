package com.example.retrofitparallelapp.data.domain.uses_cases

import com.example.retrofitparallelapp.data.domain.UsersRepository
import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSurnameUseCase @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(id: Int): BaseResponse<UserSurnameModel> {
        return repository.getUsersSurname(id)
    }
}