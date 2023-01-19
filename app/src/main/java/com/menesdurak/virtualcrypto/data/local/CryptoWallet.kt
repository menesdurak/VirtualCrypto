package com.menesdurak.virtualcrypto.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class CryptoWallet(var context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("wallet")

        val DOLLAR = doublePreferencesKey("DOLLAR")
        val BTC = doublePreferencesKey("BTC")
        val ETH = doublePreferencesKey("ETH")
    }

    suspend fun saveDollar(dollar: Double) {
        context.dataStore.edit {
            it[DOLLAR] = dollar
        }
    }

    suspend fun saveBtc(btc: Double) {
        context.dataStore.edit {
            it[BTC] = btc
        }
    }

    suspend fun saveEth(eth: Double) {
        context.dataStore.edit {
            it[ETH] = eth
        }
    }

    suspend fun readDollar(): Double  {
        val p = context.dataStore.data.first()
        return p[DOLLAR] ?: 1000.0
    }

    suspend fun readBtc(): Double  {
        val p = context.dataStore.data.first()
        return p[BTC] ?: 0.0
    }

    suspend fun readEth(): Double  {
        val p = context.dataStore.data.first()
        return p[ETH] ?: 0.0
    }
}