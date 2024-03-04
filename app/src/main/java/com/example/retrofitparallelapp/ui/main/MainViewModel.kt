package com.example.retrofitparallelapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitparallelapp.data.domain.model.error.ErrorModel
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUserSurnameUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersJobUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersListUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersSalaryUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.PostUsersPayrollUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserListUseCase: GetUsersListUseCase
) : ViewModel() {

    private val isProgressVisible = MutableStateFlow(true)
    var isProgressVisibleFlow: Flow<Boolean> = isProgressVisible

    private val listUsersMutableStateFlow = MutableStateFlow<ArrayList<UserNameModel>>(arrayListOf())
    val listUsersStateFlow: StateFlow<ArrayList<UserNameModel>> = listUsersMutableStateFlow

    private val listUsersErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val listUsersErrorSharedFlow: SharedFlow<ErrorModel> = listUsersErrorMutableSharedFlow
    fun getListUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserListUseCase().collect {
                when (it) {
                    is BaseResponse.Error -> {
                        listUsersErrorMutableSharedFlow.emit(it.error)
                    }
                    is BaseResponse.Success -> {
                        val arrayList = ArrayList(it.data.names)
                        listUsersMutableStateFlow.value = arrayList
                        isProgressVisible.value = false
                    }
                }
            }
        }
    }
}