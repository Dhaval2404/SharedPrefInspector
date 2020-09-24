package com.github.dhaval2404.sharedprefinspector.screens.share_pref_listing

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.SharedPrefManager
import kotlinx.android.synthetic.main.activity_shared_pref_listing.*
import kotlinx.android.synthetic.main.dialog_shared_pref_info.view.*

class SharedPrefListingActivity : AppCompatActivity(R.layout.activity_shared_pref_listing) {

    private lateinit var mSharedPrefManager: SharedPrefManager
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mSharedPrefListingAdapter: SharedPrefListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharePref = intent.getStringExtra("shared_pref")

        mSharedPrefManager = SharedPrefManager(this, sharePref!!)
        mSharedPreferences= getSharedPreferences(sharePref, Context.MODE_PRIVATE)

        mSharedPrefListingAdapter = SharedPrefListingAdapter()
        mSharedPrefListingAdapter.setItemClickListener {
            showSharePrefInfo(it)
        }
        sharedPrefRV.adapter = mSharedPrefListingAdapter

        refresh()
    }

    private fun refresh(){
        val sharedPrefs: List<Pair<String, Any?>> =
            mSharedPreferences.all.map { it.key to it.value }.toList()
        mSharedPrefListingAdapter.refresh(sharedPrefs)
    }

    private fun showSharePrefInfo(pair: Pair<String, Any?>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SharePref Info")

        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_shared_pref_info, null)
        builder.setView(view)

        view.keyEt.setText(pair.first)
        pair.second?.let {
            view.valueEt.setText(it.toString())
        }

        builder.setPositiveButton("Update") { _, _ ->

        }

        builder.setNegativeButton("Cancel", null)

        builder.setNeutralButton("Delete") { _, _ ->
            mSharedPrefManager.remove(pair.first)
            refresh()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

}