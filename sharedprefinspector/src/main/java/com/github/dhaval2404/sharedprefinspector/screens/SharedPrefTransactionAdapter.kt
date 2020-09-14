package com.github.dhaval2404.sharedprefinspector.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import kotlinx.android.synthetic.main.adapter_shared_pref_transaction.view.*

class SharedPrefTransactionAdapter :
    RecyclerView.Adapter<SharedPrefTransactionAdapter.SharedPrefViewHolder>() {

    private val mSharedPrefTransactions = ArrayList<SharedPref>()

    override fun getItemCount() = mSharedPrefTransactions.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SharedPrefViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = inflater.inflate(R.layout.adapter_shared_pref_transaction, parent, false)
        return SharedPrefViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: SharedPrefViewHolder, position: Int) {
        val sharedPref = mSharedPrefTransactions[position]
        holder.actionTxt.text = sharedPref.action
        holder.keyTxt.text = sharedPref.key
        holder.valueTxt.text = sharedPref.value
        holder.valueTxt.isVisible = sharedPref.value != null
    }

    class SharedPrefViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val actionTxt = view.action
        internal val keyTxt = view.key
        internal val valueTxt = view.value
    }

    fun refresh(list: List<SharedPref>) {
        mSharedPrefTransactions.clear()
        mSharedPrefTransactions.addAll(list)
        notifyDataSetChanged()
    }

}