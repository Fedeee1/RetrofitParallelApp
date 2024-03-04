package com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.list_users_mapper

import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserNameResponse

class UserNameMapper : ResponseMapper<UserNameResponse, UserNameModel> {
    override fun fromResponse(response: UserNameResponse): UserNameModel {
        return UserNameModel(response.name, response.id)
    }
}