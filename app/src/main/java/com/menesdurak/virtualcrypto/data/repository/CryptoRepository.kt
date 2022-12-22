package com.menesdurak.virtualcrypto.data.repository

import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.remote.regres.CryptoResponse

class CryptoRepository(private val cryptoApi: CryptoApi) {

    suspend fun getAllCryptos(): CryptoResponse = cryptoApi.getAllCryptos()
}