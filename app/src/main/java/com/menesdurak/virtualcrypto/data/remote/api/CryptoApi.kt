package com.menesdurak.virtualcrypto.data.remote.api

import com.menesdurak.virtualcrypto.BuildConfig
import com.menesdurak.virtualcrypto.data.remote.regres.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoApi {

    @GET("data/pricemultifull")
    suspend fun getAllCryptos(
        @Query("fsyms") crypto: String = "BTC,ETH,BUSD,USDT,XRP,USDC,BNB,DOGE,LTC,MATIC,DYDX,ADA,ETC,ATOM,LINK,SOL,DOT,XMR,APE,TRX",
        @Query("tsyms") currency: String = "USD",
        @Query("api_key") apiKey: String = BuildConfig.CRYPTO_API_KEY
    ): CryptoResponse
}