package com.menesdurak.virtualcrypto.ui.list

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.menesdurak.virtualcrypto.R
import com.menesdurak.virtualcrypto.model.Usd

class CryptoListAdapter(private val cryptoList: List<Usd>) :
    RecyclerView.Adapter<CryptoListAdapter.CryptoViewHolder>() {
    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_crypto_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.rowSymbol).text =
            cryptoList[position].FROMSYMBOL
        holder.itemView.findViewById<TextView>(R.id.rowPrice).text =
            cryptoList[position].PRICE.toString()

        if (position % 2 == 0) {
            holder.itemView.findViewById<LinearLayout>(R.id.rowLayout)
                .setBackgroundColor(Color.parseColor("#1f1f1f"))
        } else {
            holder.itemView.findViewById<LinearLayout>(R.id.rowLayout)
                .setBackgroundColor(Color.parseColor("#2f2f2f"))
        }
    }

    override fun getItemCount(): Int = cryptoList.size
}