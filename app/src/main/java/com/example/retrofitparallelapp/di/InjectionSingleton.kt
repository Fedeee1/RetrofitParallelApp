package com.example.retrofitparallelapp.di

import com.example.retrofitparallelapp.core.RetrofitClient
import com.example.retrofitparallelapp.data.domain.repository.remote.RemoteApiService

class InjectionSingleton {
    companion object {
        fun provideApiServices(): RemoteApiService {
            return RetrofitClient.makeRetrofitService().create(RemoteApiService::class.java)
        }
    }
}