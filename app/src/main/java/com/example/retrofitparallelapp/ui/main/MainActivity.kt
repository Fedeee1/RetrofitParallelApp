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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitparallelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}