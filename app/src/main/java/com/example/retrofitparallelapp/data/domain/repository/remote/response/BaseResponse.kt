package com.example.retrofitparallelapp.data.domain.repository.remote.response

import com.example.retrofitparallelapp.data.domain.model.error.ErrorModel

sealed class BaseResponse<T> {
    class Success<T>(val data: T) : BaseResponse<T>()
    class Error<T>(val error: ErrorModel) : BaseResponse<T>()
}