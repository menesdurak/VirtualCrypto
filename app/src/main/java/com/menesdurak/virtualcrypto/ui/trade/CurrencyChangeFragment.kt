package com.menesdurak.virtualcrypto.ui.trade

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import com.menesdurak.virtualcrypto.R
import com.menesdurak.virtualcrypto.data.local.CryptoWallet
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.FragmentCurrencyChange2Binding
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

    private var _binding: FragmentCurrencyChange2Binding? = null
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
        _binding = FragmentCurrencyChange2Binding.inflate(inflater, container, false)
        val view = binding.root

        if (savedInstanceState == null) {
            childFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container_view)
            }
        }

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

            binding.tvCurrencyAmount.text = currentDollar.toString()
            binding.tvBtcAmount.text = currentBtc.toString()
            binding.tvEthAmount.text = currentEth.toString()
            binding.tvXrpAmount.text = currentXrp.toString()
            binding.tvUsdcAmount.text = currentUsdc.toString()
            binding.tvBnbAmount.text = currentBnb.toString()
            binding.tvDogeAmount.text = currentDoge.toString()
            binding.tvLtcAmount.text = currentLtc.toString()
            binding.tvMaticAmount.text = currentMatic.toString()
            binding.tvDydxAmount.text = currentDydx.toString()
            binding.tvAdaAmount.text = currentAda.toString()
        }

        binding.btnToList.setOnClickListener {
            findNavController().navigate(R.id.cryptoListFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}