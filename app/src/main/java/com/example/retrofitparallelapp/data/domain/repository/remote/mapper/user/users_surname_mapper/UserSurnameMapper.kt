package com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_surname_mapper

import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserNameResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSurnameResponse

class UserSurnameMapper : ResponseMapper<UserSurnameResponse, UserSurnameModel> {
    override fun fromResponse(response: UserSurnameResponse): UserSurnameModel {
        return UserSurnameModel(response.name, response.surname)
    }
}