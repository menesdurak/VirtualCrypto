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
        val XRP = doublePreferencesKey("XRP")
        val USDC = doublePreferencesKey("USDC")
        val BNB = doublePreferencesKey("BNB")
        val DOGE = doublePreferencesKey("DOGE")
        val LTC = doublePreferencesKey("LTC")
        val MATIC = doublePreferencesKey("MATIC")
        val DYDX = doublePreferencesKey("DYDX")
        val ADA = doublePreferencesKey("ADA")
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

    suspend fun saveXrp(xrp: Double) {
        context.dataStore.edit {
            it[XRP] = xrp
        }
    }

    suspend fun saveUsdc(usdc: Double) {
        context.dataStore.edit {
            it[USDC] = usdc
        }
    }

    suspend fun saveBnb(bnb: Double) {
        context.dataStore.edit {
            it[BNB] = bnb
        }
    }

    suspend fun saveDoge(doge: Double) {
        context.dataStore.edit {
            it[DOGE] = doge
        }
    }

    suspend fun saveLtc(ltc: Double) {
        context.dataStore.edit {
            it[LTC] = ltc
        }
    }

    suspend fun saveMatic(matic: Double) {
        context.dataStore.edit {
            it[MATIC] = matic
        }
    }

    suspend fun saveDydx(dydx: Double) {
        context.dataStore.edit {
            it[DYDX] = dydx
        }
    }

    suspend fun saveAda(ada: Double) {
        context.dataStore.edit {
            it[ADA] = ada
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

    suspend fun readXrp(): Double  {
        val p = context.dataStore.data.first()
        return p[XRP] ?: 0.0
    }

    suspend fun readUsdc(): Double  {
        val p = context.dataStore.data.first()
        return p[USDC] ?: 0.0
    }

    suspend fun readBnb(): Double  {
        val p = context.dataStore.data.first()
        return p[BNB] ?: 0.0
    }

    suspend fun readDoge(): Double  {
        val p = context.dataStore.data.first()
        return p[DOGE] ?: 0.0
    }

    suspend fun readLtc(): Double  {
        val p = context.dataStore.data.first()
        return p[LTC] ?: 0.0
    }

    suspend fun readMatic(): Double  {
        val p = context.dataStore.data.first()
        return p[MATIC] ?: 0.0
    }

    suspend fun readDydx(): Double  {
        val p = context.dataStore.data.first()
        return p[DYDX] ?: 0.0
    }

    suspend fun readAda(): Double  {
        val p = context.dataStore.data.first()
        return p[ADA] ?: 0.0
    }
}