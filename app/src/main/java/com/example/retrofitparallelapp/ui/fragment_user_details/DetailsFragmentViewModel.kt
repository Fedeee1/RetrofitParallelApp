package com.example.retrofitparallelapp.ui.fragment_user_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitparallelapp.data.domain.model.error.ErrorModel
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUserSurnameUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersJobUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersSalaryUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.PostUsersPayrollUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val getUserSurnameUseCase: GetUserSurnameUseCase,
    private val getUsersJobUseCase: GetUsersJobUseCase,
    private val getUsersSalaryUseCase: GetUsersSalaryUseCase,
    private val postUsersPayrollUseCase: PostUsersPayrollUseCase
) : ViewModel() {

    private val isShimmerVisible = MutableStateFlow(true)
    var isShimmerVisibleFlow: Flow<Boolean> = isShimmerVisible

    private val usersSurnameMutableStateFlow = MutableStateFlow<UserSurnameModel>(UserSurnameModel("",""))
    val usersSurnameStateFlow: StateFlow<UserSurnameModel> = usersSurnameMutableStateFlow

    private val usersSurnameErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersSurnameErrorSharedFlow: SharedFlow<ErrorModel> = usersSurnameErrorMutableSharedFlow
    fun getUsersSurname(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserSurnameUseCase(id).collect {
                when (it) {
                    is BaseResponse.Error -> {
                        usersSurnameErrorMutableSharedFlow.emit(it.error)
                    }
                    is BaseResponse.Success -> {
                        usersSurnameMutableStateFlow.value = it.data
                        isShimmerVisible.value = true
                    }
                }
            }

        }
    }

    private val usersJobMutableStateFlow = MutableStateFlow(UserJobModel("",""))
    val usersJobStateFlow: StateFlow<UserJobModel> = usersJobMutableStateFlow

    private val usersJobErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersJobErrorSharedFlow: SharedFlow<ErrorModel> = usersJobErrorMutableSharedFlow
    fun getUsersJob(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
                getUsersJobUseCase(id).collect {
                    when (it) {
                        is BaseResponse.Error -> {
                            usersJobErrorMutableSharedFlow.emit(it.error)
                        }
                        is BaseResponse.Success -> {
                            usersJobMutableStateFlow.value = it.data
                            isShimmerVisible.value = true
                        }
                    }
            }
        }
    }

    private val usersSalaryMutableStateFlow = MutableStateFlow(UserSalaryModel("",0.0, 0.0))
    val usersSalaryStateFlow: StateFlow<UserSalaryModel> = usersSalaryMutableStateFlow

    private val usersSalaryErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersSalaryErrorSharedFlow: SharedFlow<ErrorModel> = usersSalaryErrorMutableSharedFlow
    fun getUsersSalary(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
                getUsersSalaryUseCase(id).collect {
                    when (it) {
                        is BaseResponse.Error -> {
                            usersSalaryErrorMutableSharedFlow.emit(it.error)
                        }
                        is BaseResponse.Success -> {
                            usersSalaryMutableStateFlow.value = it.data
                            isShimmerVisible.value = false
                        }
                    }
                }
        }
    }

    private val usersPayrollMutableStateFlow = MutableStateFlow(UserPayrollModel("", "", "" ,0.0, 0.0))
    val usersPayrollStateFlow: StateFlow<UserPayrollModel> = usersPayrollMutableStateFlow

    private val usersPayrollErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersPayrollErrorSharedFlow: SharedFlow<ErrorModel> = usersPayrollErrorMutableSharedFlow
    fun postUsersPayroll(user: UserPayrollModel) {
        viewModelScope.launch(Dispatchers.IO) {
            postUsersPayrollUseCase(user).collect {
                when (it) {
                    is BaseResponse.Error -> {
                        usersPayrollErrorMutableSharedFlow.emit(it.error)
                    }
                    is BaseResponse.Success -> {
                        usersPayrollMutableStateFlow.value = it.data
                        isShimmerVisible.value = true
                    }
                }
            }
        }
    }
}