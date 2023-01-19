package com.menesdurak.virtualcrypto.ui.trade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.menesdurak.virtualcrypto.R
import com.menesdurak.virtualcrypto.data.local.CryptoWallet
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.FragmentCurrencyChangeBinding
import com.menesdurak.virtualcrypto.model.Usd
import com.menesdurak.virtualcrypto.ui.list.CryptoListFragment
import com.menesdurak.virtualcrypto.ui.viewmodel.CryptoViewModel
import com.menesdurak.virtualcrypto.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class CurrencyChangeFragment : Fragment() {

    private var _binding: FragmentCurrencyChangeBinding? = null
    private val binding get() = _binding!!

    private var currentDollar: Double = 0.0
    private var currentBtc: Double = 0.0
    private var currentEth: Double = 0.0

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
        _binding = FragmentCurrencyChangeBinding.inflate(inflater, container, false)
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
            Toast.makeText(requireContext(), "$: $currentDollar btc: $currentBtc eth: $currentEth", Toast.LENGTH_SHORT).show()
        }

        val sortedCurrencySymbolsList = listOf("BTC", "ETH", "XRP", "USDC", "BNB",
            "DOGE", "LTC", "MATIC", "DYDX", "ADA", "ETC", "ATOM", "LINK").sorted()

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            sortedCurrencySymbolsList
            ) .also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCrypto.adapter = it
        }

        binding.btnToList.setOnClickListener {
            findNavController().navigate(R.id.cryptoListFragment)
        }

        binding.btnBuy.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if(currentDollar >= binding.etBuy.text.toString().toDouble()) {
                    when(binding.spinnerCrypto.selectedItem.toString()) {
                        "BTC" -> {
                            ap.saveBtc(
                                binding.etBuy.text.toString().toDouble()
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.BTC.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - binding.etBuy.text.toString().toDouble()
                            )
                            findNavController().navigate(R.id.cryptoListFragment)
                        }
                        "ETH" -> {
                            ap.saveEth(
                                binding.etBuy.text.toString().toDouble()
                                        *(1/cryptoViewModel.cryptoList.value!!.RAW.ETH.USD.PRICE.toDouble())
                            )
                            ap.saveDollar(
                                currentDollar - binding.etBuy.text.toString().toDouble()
                            )
                            findNavController().navigate(R.id.cryptoListFragment)
                        }
                    }
                } else {
                    Toast
                        .makeText(requireContext(), "Your money is not enough!", Toast.LENGTH_SHORT)
                        .show()
                }
                println(ap.readDollar())
                println(ap.readBtc())
                println(ap.readEth())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}