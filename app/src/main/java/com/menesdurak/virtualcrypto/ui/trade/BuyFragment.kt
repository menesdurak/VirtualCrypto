package com.menesdurak.virtualcrypto.ui.trade

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.menesdurak.virtualcrypto.data.local.CryptoWallet
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.FragmentBuyBinding
import com.menesdurak.virtualcrypto.databinding.FragmentCryptoListBinding
import com.menesdurak.virtualcrypto.ui.viewmodel.CryptoViewModel
import com.menesdurak.virtualcrypto.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class BuyFragment: Fragment() {
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    private var currentDollar: Double = 0.0
    private var currentBtc: Double = 0.0
    private var currentEth: Double = 0.0
    private var currentXrp: Double = 0.0
    private var currentUsdc: Double = 0.0
    private var currentBnb: Double = 0.0
    private var currentDoge: Double = 0.0
    private var currentLtc: Double = 0.0
    private var currentMatic: Double = 0.0
    private var currentDydx: Double = 0.0
    private var currentAda: Double = 0.0

    private val retrofit: Retrofit by lazy {
        RetrofitClient.getInstance()
    }
    private val cryptoApi: CryptoApi by lazy {
        retrofit.create(CryptoApi::class.java)
    }
    private val cryptoRepository: CryptoRepository by lazy {
        CryptoRepository(cryptoApi)
    }
    private val cryptoViewModel: CryptoViewModel by viewModels {
        ViewModelFactory(cryptoRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoViewModel.getAllCryptos()

        val ap = CryptoWallet(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            currentDollar = ap.readDollar()
            currentBtc = ap.readBtc()
            currentEth = ap.readEth()
            currentXrp = ap.readXrp()
            currentUsdc = ap.readUsdc()
            currentBnb = ap.readBnb()
            currentDoge = ap.readDoge()
            currentLtc = ap.readLtc()
            currentMatic = ap.readMatic()
            currentDydx = ap.readDydx()
            currentAda = ap.readAda()
        }

        val sortedCurrencySymbolsList = listOf("BTC", "ETH", "XRP", "USDC", "BNB",
            "DOGE", "LTC", "MATIC", "DYDX", "ADA").sorted()

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            sortedCurrencySymbolsList
        ) .also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCrypto.adapter = it
        }

        binding.btnBuy.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val enteredValue = binding.etBuy.text.toString().toDouble()
                if(currentDollar >= enteredValue && enteredValue > 0.0) {
                    when(binding.spinnerCrypto.selectedItem.toString()) {
                        "BTC" -> {
                            ap.saveBtc(
                                currentBtc + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.BTC.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "ETH" -> {
                            ap.saveEth(
                                currentEth + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.ETH.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "XRP" -> {
                            ap.saveXrp(
                                currentXrp + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.XRP.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "USDC" -> {
                            ap.saveUsdc(
                                currentUsdc + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.USDC.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "BNB" -> {
                            ap.saveBnb(
                                currentBnb + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.BNB.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "DOGE" -> {
                            ap.saveDoge(
                                currentDoge + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.DOGE.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "LTC" -> {
                            ap.saveLtc(
                                currentLtc + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.LTC.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "MATIC" -> {
                            ap.saveMatic(
                                currentMatic + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.MATIC.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "DYDX" -> {
                            ap.saveDydx(
                                currentDydx + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.DYDX.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                        "ADA" -> {
                            ap.saveAda(
                                currentAda + enteredValue
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.ADA.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - enteredValue
                            )
                        }
                    }
                    findNavController().navigate(com.menesdurak.virtualcrypto.R.id.cryptoListFragment)
                } else if (currentDollar < enteredValue) {
                    Toast
                        .makeText(requireContext(), "Your money is not enough!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast
                        .makeText(requireContext(), "Couldn't buy.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}