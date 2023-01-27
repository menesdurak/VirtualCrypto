package com.menesdurak.virtualcrypto.ui

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.ActivityMainBinding
import com.menesdurak.virtualcrypto.ui.viewmodel.CryptoViewModel
import com.menesdurak.virtualcrypto.ui.viewmodel.ViewModelFactory
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}