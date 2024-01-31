package com.jordichorro.tema10app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jordichorro.tema10app1.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random
import retrofit2.Response
import com.jordichorro.tema10app1.CatImageResponse


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchRandom(Random.nextInt(1, 50).toString())
        binding.btnSig.setOnClickListener {
            searchRandom(Random.nextInt(1, 50).toString())
        }
    }

    //APP INCOMPLETA

    private fun getRetrofit1(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://meowfacts.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getRetrofit2(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cataas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchRandom(num: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit1().create(APIService::class.java).getCatFact("?id=$num")
            val cat: CatResponse? = call.body()

            runOnUiThread() {
                if (call.isSuccessful) {
                    //Mostramos el texto
                    val data: String? = cat?.data?.get(0)
                    showInIU(data)
                } else {
                    //Mostramos un error
                    showError()
                }
            }
        }
    }
    private fun showInIU(data: String?) {
        binding.tvImagen.text = data
    }

    private fun showError() {
        runOnUiThread {
            Toast.makeText(this, "Ha habido un error", Toast.LENGTH_SHORT).show()
        }
    }
}