package com.example.retrofitparallelapp.ui.main
/*
La idea es que hagáis un listado que muestre los diferentes usuarios.
Cuando se pulse en un usuario habrá que ir a su detalle, mostrar todos sus datos,
para lo que habrá que lanzar el resto de las llamadas para conseguirlos, y para la última “Payroll”,
hay que tener los datos previos.
Lo que se busca, que lancéis las llamadas en paralelo, esperéis la ejecución de estas y por último la llamada final,
para poder visualizar todos los datos en pantalla.
Mientras, la información se va pintando debéis mostrar al usuario que se está procesando información ya sea con un loading,
con skeletons, o lo que consideréis.
Puntos para mejorar que siempre los esperáis jejeje, pruebas a través de logs,
para que podáis garantizar que las llamadas se ejecutan en paralelo y cómo queremos.
No os rayéis si veis tiempo muy dispares todos los métodos tiene un tiempo de respuesta variable,
para simular que el back no siempre responda con la misma rapidez.
*/

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitparallelapp.R
import com.example.retrofitparallelapp.commons.USER_JOB_KEY
import com.example.retrofitparallelapp.commons.USER_KEY
import com.example.retrofitparallelapp.commons.USER_SALARY_KEY
import com.example.retrofitparallelapp.commons.USER_SURNAME_KEY
import com.example.retrofitparallelapp.data.domain.model.user.UserJobModel
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSalaryModel
import com.example.retrofitparallelapp.data.domain.model.user.UserSurnameModel
import com.example.retrofitparallelapp.databinding.ActivityMainBinding
import com.example.retrofitparallelapp.ui.fragment_user_details.DetailsUserFragment
import com.example.retrofitparallelapp.ui.main.adapter.RecyclerUsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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
        val adapter = RecyclerUsersAdapter(listUsers, this, this)
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