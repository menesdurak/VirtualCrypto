package com.menesdurak.virtualcrypto.data.remote

import com.menesdurak.virtualcrypto.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitClient {
    companion object {
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}