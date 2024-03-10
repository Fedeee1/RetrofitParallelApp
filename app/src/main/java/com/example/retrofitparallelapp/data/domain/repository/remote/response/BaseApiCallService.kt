package com.example.retrofitparallelapp.data.domain.repository.remote.response

import android.util.Log
import com.example.retrofitparallelapp.BuildConfig
import com.example.retrofitparallelapp.data.domain.model.error.ErrorModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.error.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiCallService {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>) : BaseResponse<T> {
        val response : Response<T>
        try {
            response = call.invoke()

            return if (!response.isSuccessful){
                val errorResponse = mapErrorResponse(response)
                Log.e("TAG", "errorResponse: ${errorResponse.message}")
                BaseResponse.Error(errorResponse)
            } else {
                response.body()?.let { body ->
                    BaseResponse.Success(body)
                } ?: BaseResponse.Error(mapErrorResponse(response))
            }
        } catch (throwable: Throwable) {
            Log.e("TAG", "throwable: ${throwable.message}")
            throwable.printStackTrace()
            return BaseResponse.Error(mapErrorResponse(throwable))
        }
    }

    private fun <T> mapErrorResponse(response: Response<T>): ErrorModel {
        val errorBody = response.errorBody()?.string()
        val errorData = try {
            val parsedData = Gson().fromJson(errorBody, ErrorResponse::class.java)
            if (response.code() == 401) {
                parsedData.errorCode = 401.toString()
            }
            parsedData
        } catch (exception: java.lang.Exception){
            Log.e("TAG", "exception: ${exception.message}")
            exception.printStackTrace()
            null
        }
        return ErrorModel(errorData?.error ?: "", errorData?.errorCode ?: "", errorData?.message ?: "")
    }

    private fun mapErrorResponse(throwable: Throwable) : ErrorModel{
        return if (BuildConfig.DEBUG) {
            ErrorModel("Unkown mapErrorResponse", "Unkown mapErrorResponse", throwable.message ?: "Unkown mapErrorResponse" )
        } else {
            ErrorModel("Lo sentimos, estamos presentando problemas de conexión", "0", "Vuelve a intentarlo más tarde" )
        }
    }
}