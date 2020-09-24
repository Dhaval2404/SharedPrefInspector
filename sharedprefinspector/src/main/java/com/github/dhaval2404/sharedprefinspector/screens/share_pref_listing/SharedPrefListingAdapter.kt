package com.github.dhaval2404.sharedprefinspector.screens.share_pref_listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import kotlinx.android.synthetic.main.adapter_shared_pref_transaction.view.*
import java.io.File

class SharedPrefListingAdapter :
    RecyclerView.Adapter<SharedPrefListingAdapter.SharedPrefViewHolder>() {

    private val mSharedPrefs = ArrayList<Pair<String, Any?>>()
    private var mItemClickListener: ((Pair<String, Any?>) -> Unit)? = null

    fun setItemClickListener(listener: (Pair<String, Any?>) -> Unit) {
        mItemClickListener = listener
    }

    override fun getItemCount() = mSharedPrefs.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SharedPrefViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = inflater.inflate(R.layout.adapter_shared_pref_listing, parent, false)
        return SharedPrefViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: SharedPrefViewHolder, position: Int) {
        val sharedPref = mSharedPrefs[position]
        holder.itemView.tag = sharedPref
        holder.keyTxt.text = sharedPref.first
        holder.valueTxt.text = sharedPref.second?.toString()
    }

    inner class SharedPrefViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val keyTxt = view.key
        internal val valueTxt = view.value

        init {
            view.setOnClickListener {
                mItemClickListener?.invoke(it.tag as Pair<String, Any?>)
            }
        }

    }

    fun refresh(list: List<Pair<String, Any?>>) {
        mSharedPrefs.clear()
        mSharedPrefs.addAll(list)
        notifyDataSetChanged()
    }

}