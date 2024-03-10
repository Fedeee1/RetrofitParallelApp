package com.example.retrofitparallelapp.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitparallelapp.R
import com.example.retrofitparallelapp.commons.USER_KEY
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.databinding.ActivityMainBinding
import com.example.retrofitparallelapp.ui.fragment_user_details.DetailsUserFragment
import com.example.retrofitparallelapp.ui.main.adapter.RecyclerUsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerUsersAdapter.OnUserItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressLoading.isVisible = true
        setUpViewModel()
        viewModel.getListUsers()
    }

    private fun setUpViewModel() {

        var listUsers: List<UserNameModel>
        lifecycleScope.launch {
            viewModel.listUsersStateFlow.collect { dataSet ->
                listUsers = dataSet
                addRecyclerView(listUsers)
            }
        }
        lifecycleScope.launch {
            viewModel.listUsersErrorSharedFlow.collect { error ->
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            viewModel.isProgressVisibleFlow.collect {
                binding.progressLoading.isVisible = it
            }
        }
    }

    private fun addRecyclerView(
        listUsers: List<UserNameModel>,
    ) {
        val adapter = RecyclerUsersAdapter(listUsers, this)
        binding.recyclerUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerUsers.adapter = adapter
    }

    override fun onUserClick(user: UserNameModel) {
        openFragment(user)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun openFragment(
        user: UserNameModel
    ) {
        val bundle = Bundle()
        bundle.putParcelable(USER_KEY, user)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = DetailsUserFragment()
        fragment.arguments = bundle
        transaction.replace(R.id.fragmentDetails, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}