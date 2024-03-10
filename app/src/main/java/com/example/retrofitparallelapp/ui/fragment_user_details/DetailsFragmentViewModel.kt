package com.example.retrofitparallelapp.ui.fragment_user_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitparallelapp.data.domain.model.error.ErrorModel
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitparallelapp.data.domain.repository.remote.response.user.UserPayrollRequest
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUserSurnameUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersJobUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.GetUsersSalaryUseCase
import com.example.retrofitparallelapp.data.domain.uses_cases.PostUsersPayrollUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    fun getUserDetails(id: Int) {
        viewModelScope.launch {
            isShimmerVisible.value = true
            val deferred = arrayListOf(
                async { getUsersSurname(id) },
                async { getUsersJob(id) },
                async { getUsersSalary(id) }

            )
            deferred.awaitAll()
            Log.d(
                "Detalles",
                UserPayrollRequest(
                    id,
                    usersSurnameStateFlow.value.name,
                    usersSurnameStateFlow.value.surname,
                    usersJobStateFlow.value.job,
                    usersSalaryStateFlow.value.salary.toDouble()
                ).toString()
            )
            val deferredPayroll = async {
                postUsersPayroll(
                    UserPayrollRequest(
                        id,
                        usersSurnameStateFlow.value.name,
                        usersSurnameStateFlow.value.surname,
                        usersJobStateFlow.value.job,
                        usersSalaryStateFlow.value.salary.toDouble()
                    )
                )
            }
            deferredPayroll.await()
            isShimmerVisible.value = false
        }
    }

    private val usersSurnameMutableStateFlow =
        MutableStateFlow<UserSurnameModel>(UserSurnameModel("", ""))
    val usersSurnameStateFlow: StateFlow<UserSurnameModel> = usersSurnameMutableStateFlow

    private val usersSurnameErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersSurnameErrorSharedFlow: SharedFlow<ErrorModel> = usersSurnameErrorMutableSharedFlow
    private suspend fun getUsersSurname(id: Int) {
        when (val response = getUserSurnameUseCase(id)) {
            is BaseResponse.Error -> {
                usersSurnameErrorMutableSharedFlow.emit(response.error)
            }

            is BaseResponse.Success -> {
                usersSurnameMutableStateFlow.value = response.data
            }
        }
    }

    private val usersJobMutableStateFlow = MutableStateFlow(UserJobModel("", ""))
    val usersJobStateFlow: StateFlow<UserJobModel> = usersJobMutableStateFlow

    private val usersJobErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersJobErrorSharedFlow: SharedFlow<ErrorModel> = usersJobErrorMutableSharedFlow
    private suspend fun getUsersJob(id: Int) {
        when (val response = getUsersJobUseCase(id)) {
            is BaseResponse.Error -> {
                usersJobErrorMutableSharedFlow.emit(response.error)
            }

            is BaseResponse.Success -> {
                usersJobMutableStateFlow.value = response.data
            }
        }
    }

    private val usersSalaryMutableStateFlow = MutableStateFlow(UserSalaryModel("", 0.0, 0.0))
    val usersSalaryStateFlow: StateFlow<UserSalaryModel> = usersSalaryMutableStateFlow

    private val usersSalaryErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersSalaryErrorSharedFlow: SharedFlow<ErrorModel> = usersSalaryErrorMutableSharedFlow
    private suspend fun getUsersSalary(id: Int) {
        when (val response = getUsersSalaryUseCase(id)) {
            is BaseResponse.Error -> {
                usersSalaryErrorMutableSharedFlow.emit(response.error)
            }

            is BaseResponse.Success -> {
                usersSalaryMutableStateFlow.value = response.data
            }
        }
    }

    private val usersPayrollMutableStateFlow =
        MutableStateFlow(UserPayrollModel("", "", "", 0.0, 0.0))
    val usersPayrollStateFlow: StateFlow<UserPayrollModel> = usersPayrollMutableStateFlow

    private val usersPayrollErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val usersPayrollErrorSharedFlow: SharedFlow<ErrorModel> = usersPayrollErrorMutableSharedFlow
    private suspend fun postUsersPayroll(user: UserPayrollRequest) {
        when (val response = postUsersPayrollUseCase(user)) {
            is BaseResponse.Error -> {
                usersPayrollErrorMutableSharedFlow.emit(response.error)
            }

            is BaseResponse.Success -> {
                usersPayrollMutableStateFlow.value = response.data
                isShimmerVisible.value = true
            }
        }

    }
}