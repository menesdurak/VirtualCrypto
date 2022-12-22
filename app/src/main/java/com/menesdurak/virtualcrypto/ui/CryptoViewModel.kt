package com.menesdurak.virtualcrypto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.virtualcrypto.data.remote.regres.CryptoResponse
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import kotlinx.coroutines.launch

class CryptoViewModel(private val cryptoRepository: CryptoRepository) : ViewModel() {

    private val mutableCryptoList: MutableLiveData<CryptoResponse> = MutableLiveData()
    val cryptoList: LiveData<CryptoResponse>
        get() = mutableCryptoList

    fun getAllCryptos() {
        viewModelScope.launch {
            mutableCryptoList.value = cryptoRepository.getAllCryptos()
        }
    }

}