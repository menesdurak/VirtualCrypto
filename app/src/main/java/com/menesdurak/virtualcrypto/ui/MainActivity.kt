package com.menesdurak.virtualcrypto.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

//    private val retrofit: Retrofit by lazy {
//        RetrofitClient.getInstance()
//    }
//    private val cryptoApi: CryptoApi by lazy {
//        retrofit.create(CryptoApi::class.java)
//    }
//    private val cryptoRepository: CryptoRepository by lazy {
//        CryptoRepository(cryptoApi)
//    }
//    private val cryptoViewModel: CryptoViewModel by viewModels {
//        ViewModelFactory(cryptoRepository)
//    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

//    override fun onStart() {
//        super.onStart()
//
//        cryptoViewModel.getAllCryptos()
//
//        cryptoViewModel.cryptoList.observe(this) {
//            Toast.makeText(this, it.RAW.BTC.USD.PRICE.toString(), Toast.LENGTH_SHORT).show()
//            Toast.makeText(this, it.RAW.ETH.USD.PRICE.toString(), Toast.LENGTH_SHORT).show()
//            println(it.RAW.BTC.USD.PRICE.toString())
//            println(it.RAW.ETH.USD.PRICE.toString())
//        }
//    }
}