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
import com.example.retrofitparallelapp.data.domain.model.user.UserPayrollModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.databinding.FragmentDetailsUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class DetailsUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailsUserBinding
    private val viewModel by viewModels<DetailsFragmentViewModel>()
    private var userSurname = UserSurnameModel("", "")
    private var userJob = UserJobModel("", "")
    private var userSalary = UserSalaryModel("", 0.0, 0.0)
    private var userPayroll = UserPayrollModel("", "","", 0.0, 0.0)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val user = arguments?.getParcelable<UserNameModel>(USER_KEY)
        binding = FragmentDetailsUserBinding.inflate(inflater, container, false)

        setUpViewModel()
        viewModel.getUserDetails(user?.id!!)
        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setUpViewModel() {
        lifecycleScope.launch {
                viewModel.usersSurnameStateFlow.collect { dataSet ->
                    userSurname = dataSet
                }
                viewModel.usersSurnameErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
                viewModel.usersJobStateFlow.collect { dataSet ->
                    userJob = dataSet
                }
                viewModel.usersJobErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
                viewModel.usersSalaryStateFlow.collect { dataSet ->
                    userSalary = dataSet
                }
                viewModel.usersSalaryErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
                viewModel.usersPayrollStateFlow.collect { dataSet ->
                    userPayroll = dataSet
                    binding.txtUserTotal.text = userPayroll.total.toString()
                }
                viewModel.usersPayrollErrorSharedFlow.collect { error ->
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            viewModel.isShimmerVisibleFlow.collect { dataSet ->
                binding.shimmerLoading.isVisible = dataSet
                if (!dataSet){
                    binding.txtUserName.text = "${userSurname.name.replaceFirstChar { it.uppercaseChar()}} ${userSurname.surname.replaceFirstChar { it.uppercaseChar() }}"
                    binding.txtUserJob.text = userJob.job.replaceFirstChar { it.uppercaseChar() }
                    binding.txtUserCompany.text = userJob.company.replaceFirstChar { it.uppercaseChar()}
                    binding.txtUserSalary.text = userSalary.salary
                    binding.txtUserTax.text = userSalary.tax.toString()
                    binding.txtUserFormation.text = userSalary.formation.toString()

                }
                binding.constraintFragment.isVisible = !dataSet
            }
        }
    }
}