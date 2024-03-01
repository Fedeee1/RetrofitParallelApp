package com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user

import com.example.retrofitparallelapp.data.domain.model.user.ListNamesModel
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserNameResponse
import javax.inject.Inject

class ListUsersMapper @Inject constructor():
    ResponseMapper<List<UserNameResponse>, ListNamesModel> {
    override fun fromResponse(listUsersResponse: List<UserNameResponse>): ListNamesModel {

        val listModel = mutableListOf<UserNameModel>()

        if (!listUsersResponse.isNullOrEmpty()) {
            val userMapper = UserNameMapper()
            listUsersResponse.forEach { getListUsersResponse ->
                listModel.add(userMapper.fromResponse(getListUsersResponse))
            }
        }
        return ListNamesModel(
            listModel
        )
    }
}