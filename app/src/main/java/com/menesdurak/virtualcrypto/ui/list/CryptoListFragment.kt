package com.menesdurak.virtualcrypto.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.virtualcrypto.R
import com.menesdurak.virtualcrypto.data.remote.RetrofitClient
import com.menesdurak.virtualcrypto.data.remote.api.CryptoApi
import com.menesdurak.virtualcrypto.data.remote.regres.CryptoResponse
import com.menesdurak.virtualcrypto.data.repository.CryptoRepository
import com.menesdurak.virtualcrypto.databinding.FragmentCryptoListBinding
import com.menesdurak.virtualcrypto.model.Crypto
import com.menesdurak.virtualcrypto.model.Usd
import com.menesdurak.virtualcrypto.ui.viewmodel.CryptoViewModel
import com.menesdurak.virtualcrypto.ui.viewmodel.ViewModelFactory
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList

class CryptoListFragment : Fragment() {

    private var _binding: FragmentCryptoListBinding? = null
    private val binding get() = _binding!!
    private val mutableCryptoList: MutableList<Usd> = mutableListOf()

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

            var crypto = Usd(it.RAW.BTC.USD.FROMSYMBOL,
                it.RAW.BTC.USD.PRICE,
                it.RAW.BTC.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.ETH.USD.FROMSYMBOL,
                it.RAW.ETH.USD.PRICE,
                it.RAW.ETH.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.XRP.USD.FROMSYMBOL,
                it.RAW.XRP.USD.PRICE,
                it.RAW.XRP.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.USDC.USD.FROMSYMBOL,
                it.RAW.USDC.USD.PRICE,
                it.RAW.USDC.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.BNB.USD.FROMSYMBOL,
                it.RAW.BNB.USD.PRICE,
                it.RAW.BNB.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.DOGE.USD.FROMSYMBOL,
                it.RAW.DOGE.USD.PRICE,
                it.RAW.DOGE.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.LTC.USD.FROMSYMBOL,
                it.RAW.LTC.USD.PRICE,
                it.RAW.LTC.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.MATIC.USD.FROMSYMBOL,
                it.RAW.MATIC.USD.PRICE,
                it.RAW.MATIC.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.DYDX.USD.FROMSYMBOL,
                it.RAW.DYDX.USD.PRICE,
                it.RAW.DYDX.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.ADA.USD.FROMSYMBOL,
                it.RAW.ADA.USD.PRICE,
                it.RAW.ADA.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.ETC.USD.FROMSYMBOL,
                it.RAW.ETC.USD.PRICE,
                it.RAW.ETC.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.ATOM.USD.FROMSYMBOL,
                it.RAW.ATOM.USD.PRICE,
                it.RAW.ATOM.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            crypto = Usd(it.RAW.LINK.USD.FROMSYMBOL,
                it.RAW.LINK.USD.PRICE,
                it.RAW.LINK.USD.LASTUPDATE)
            mutableCryptoList.add(crypto)

            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val cryptoAdapter = CryptoListAdapter(mutableCryptoList)
            binding.recyclerView.adapter = cryptoAdapter
        }

        binding.btnToWallet.setOnClickListener {
            findNavController().navigate(R.id.currencyChangeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}