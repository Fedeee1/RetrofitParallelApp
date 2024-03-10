package com.example.retrofitparallelapp.data.domain.repository.remote.mapper.user.users_job_mapper

import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserJobResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserSurnameResponse

class UserJobMapper : ResponseMapper<UserJobResponse, UserJobModel> {
    override fun fromResponse(response: UserJobResponse): UserJobModel {
        return UserJobModel(response.job, response.company)
    }
}