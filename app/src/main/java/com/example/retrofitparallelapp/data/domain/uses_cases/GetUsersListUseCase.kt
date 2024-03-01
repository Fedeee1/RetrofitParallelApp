package com.example.retrofitparallelapp.data.domain.uses_cases

import com.example.retrofitparallelapp.data.domain.UsersRepository
import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val repository: UsersRepository){
    operator fun invoke(limit: Int, offset: Int): Flow<BaseResponse<ListNamesModel>> {
        return repository.getListUsers(limit, offset)
    }
}