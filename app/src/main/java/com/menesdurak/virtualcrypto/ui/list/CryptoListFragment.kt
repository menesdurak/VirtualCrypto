package com.menesdurak.virtualcrypto.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.remote.regres.CryptoResponse
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.FragmentCryptoListBinding
import com.menesdurak.virtualcrypto.ui.viewmodel.CryptoViewModel
import com.menesdurak.virtualcrypto.ui.viewmodel.ViewModelFactory
import retrofit2.Retrofit

class CryptoListFragment : Fragment() {

    private var _binding: FragmentCryptoListBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentCryptoListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoViewModel.getAllCryptos()

        cryptoViewModel.cryptoList.observe(viewLifecycleOwner) {
            binding.tvCryptoSymbol1.text = it.RAW.BTC.USD.FROMSYMBOL
            binding.tvCryptoSymbol2.text = it.RAW.ETH.USD.FROMSYMBOL
            binding.tvCryptoSymbol3.text = it.RAW.ADA.USD.FROMSYMBOL
            binding.tvCryptoSymbol4.text = it.RAW.APE.USD.FROMSYMBOL
            binding.tvCryptoSymbol5.text = it.RAW.ATOM.USD.FROMSYMBOL
            binding.tvCryptoSymbol6.text = it.RAW.BNB.USD.FROMSYMBOL

            binding.tvCryptoPrice1.text = it.RAW.BTC.USD.PRICE.toString()
            binding.tvCryptoPrice2.text = it.RAW.ETH.USD.PRICE.toString()
            binding.tvCryptoPrice3.text = it.RAW.ADA.USD.PRICE.toString()
            binding.tvCryptoPrice4.text = it.RAW.APE.USD.PRICE.toString()
            binding.tvCryptoPrice5.text = it.RAW.ATOM.USD.PRICE.toString()
            binding.tvCryptoPrice6.text = it.RAW.BNB.USD.PRICE.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}