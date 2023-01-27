package com.menesdurak.virtualcrypto.ui.trade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.menesdurak.virtualcrypto.R
import com.menesdurak.virtualcrypto.data.local.CryptoWallet
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.FragmentBuyBinding
import com.menesdurak.virtualcrypto.databinding.FragmentSellBinding
import com.menesdurak.virtualcrypto.ui.viewmodel.CryptoViewModel
import com.menesdurak.virtualcrypto.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class SellFragment : Fragment() {
    private var _binding: FragmentSellBinding? = null
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
        _binding = FragmentSellBinding.inflate(inflater, container, false)
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

        val sortedCurrencySymbolsList = listOf(
            "BTC", "ETH", "XRP", "USDC", "BNB",
            "DOGE", "LTC", "MATIC", "DYDX", "ADA"
        ).sorted()

        ArrayAdapter(
            requireContext(),
            R.layout.my_selected_item,
            sortedCurrencySymbolsList
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCrypto.adapter = it
        }

        binding.btnSell.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val enteredValue = binding.etBuy.text.toString().toDouble()
                if (enteredValue > 0.0) {
                    when (binding.spinnerCrypto.selectedItem.toString()) {
                        "BTC" -> {
                            if (currentBtc - enteredValue >= 0) {
                                ap.saveBtc(currentBtc - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.BTC.USD.PRICE.toDouble())
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "ETH" -> {
                            if (currentEth - enteredValue >= 0) {
                                ap.saveEth(currentEth - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.ETH.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "XRP" -> {
                            if (currentXrp - enteredValue >= 0) {
                                ap.saveXrp(currentXrp - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.XRP.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "USDC" -> {
                            if (currentUsdc - enteredValue >= 0) {
                                ap.saveUsdc(currentUsdc - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.USDC.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "BNB" -> {
                            if (currentBnb - enteredValue >= 0) {
                                ap.saveBnb(currentBnb - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.BNB.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "DOGE" -> {
                            if (currentDoge - enteredValue >= 0) {
                                ap.saveDoge(currentDoge - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.DOGE.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "LTC" -> {
                            if (currentLtc - enteredValue >= 0) {
                                ap.saveLtc(currentLtc - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.LTC.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "MATIC" -> {
                            if (currentMatic - enteredValue >= 0) {
                                ap.saveMatic(currentMatic - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.MATIC.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "DYDX" -> {
                            if (currentDydx - enteredValue >= 0) {
                                ap.saveDydx(currentDydx - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.DYDX.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                        "ADA" -> {
                            if (currentAda - enteredValue >= 0) {
                                ap.saveAda(currentAda - enteredValue)
                                ap.saveDollar(
                                    currentDollar + enteredValue
                                            * (cryptoViewModel.cryptoList.value!!.RAW.ADA.USD.PRICE.toDouble()
                                            )
                                )
                            } else {
                                Toast
                                    .makeText(
                                        requireContext(),
                                        "Couldn't sell.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                    }
                    findNavController().navigate(com.menesdurak.virtualcrypto.R.id.cryptoListFragment)
                }
            }
        }
    }
}