package com.menesdurak.virtualcrypto.ui.trade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.menesdurak.virtualcrypto.databinding.FragmentCurrencyChangeBinding
import com.menesdurak.virtualcrypto.ui.list.CryptoListFragment

class CurrencyChangeFragment : Fragment() {

    private var _binding: FragmentCurrencyChangeBinding? = null
    private val binding get() = _binding!!

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

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("BTC", "ETH", "A", "B", "C")
            ) .also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCrypto.adapter = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}