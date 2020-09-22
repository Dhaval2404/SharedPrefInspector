package com.github.dhaval2404.sharedprefinspector.screens.shared_pref_file_listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dhaval2404.sharedprefinspector.R
import kotlinx.android.synthetic.main.adapter_shared_pref_files_list.view.*
import java.io.File

class SharedPrefFileListingAdapter :
    RecyclerView.Adapter<SharedPrefFileListingAdapter.SharedPrefViewHolder>() {

    private val mItems = ArrayList<File>()

    override fun getItemCount() = mItems.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SharedPrefViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = inflater.inflate(R.layout.adapter_shared_pref_files_list, parent, false)
        return SharedPrefViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: SharedPrefViewHolder, position: Int) {
        holder.sharedPrefFileTxt.text = mItems[position].nameWithoutExtension
    }

    class SharedPrefViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val sharedPrefFileTxt = view.sharedPrefFile
    }

    fun refresh(list: List<File>) {
        mItems.clear()
        mItems.addAll(list)
        notifyDataSetChanged()
    }

}