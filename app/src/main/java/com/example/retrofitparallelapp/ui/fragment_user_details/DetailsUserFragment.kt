package com.example.retrofitparallelapp.ui.fragment_user_details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.retrofitparallelapp.commons.USER_KEY
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.databinding.FragmentDetailsUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class DetailsUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailsUserBinding
    private val viewModel by viewModels<DetailsFragmentViewModel>()
    private var userSurname = UserSurnameModel("", "")
    private var userJob = UserJobModel("", "")
    private var userSalary = UserSalaryModel("", 0.0, 0.0)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val user = arguments?.getParcelable<UserNameModel>(USER_KEY)
        binding = FragmentDetailsUserBinding.inflate(inflater, container, false)

        setUpViewModel(user!!)

        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setUpViewModel(user: UserNameModel) {
        lifecycleScope.launch {
            val deferred1 = async {
                viewModel.usersSurnameStateFlow.collect { dataSet ->
                    userSurname = dataSet
                }
                viewModel.usersSurnameErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
                }
            }
            deferred1.await()
        }
        lifecycleScope.launch {
            val deferred2 = async {
                viewModel.usersJobStateFlow.collect { dataSet ->
                    userJob = dataSet
                }
                viewModel.usersJobErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
                }
            }
            deferred2.await()
        }
        lifecycleScope.launch {
            val deferred3 = async {
                viewModel.usersSalaryStateFlow.collect { dataSet ->
                    userSalary = dataSet
                    binding.txtUserName.text = "${user.name.replaceFirstChar { it.uppercaseChar()}} ${userSurname.surname.replaceFirstChar { it.uppercaseChar() }}"
                    binding.txtUserJob.text =
                        "Trabajo: ${
                            userJob.job.replaceFirstChar { it.uppercaseChar() }
                        }\n" +
                                "Companía: ${
                                    userJob.company.replaceFirstChar { it.uppercaseChar() }
                                }"
                    binding.txtUserSalary.text =
                        "Salario: ${userSalary.salary}\nTax: ${userSalary.tax}\nFormación: ${userSalary.formation}"

                }
                viewModel.usersSalaryErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
                }
            }
            deferred3.await()
        }

        lifecycleScope.launch {
            viewModel.isShimmerVisibleFlow.collect {
                binding.constraintFragmentLoading.isVisible = it
                binding.scrollFragment.isVisible = !it
                binding.constraintFragment.isVisible = !it
            }
        }

        if (!binding.constraintFragmentLoading.isVisible){

        }

        lifecycleScope.launch {
            viewModel.getUsersSurname(user.id)
            viewModel.getUsersJob(user.id)
            viewModel.getUsersSalary(user.id)
        }

    }
}