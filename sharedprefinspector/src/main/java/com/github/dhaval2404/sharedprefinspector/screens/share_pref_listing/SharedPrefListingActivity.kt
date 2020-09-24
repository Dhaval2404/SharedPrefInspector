package com.github.dhaval2404.sharedprefinspector.screens.share_pref_listing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.sharedprefinspector.R
import kotlinx.android.synthetic.main.activity_shared_pref_listing.*
import kotlinx.android.synthetic.main.dialog_shared_pref_info.view.*

class SharedPrefListingActivity : AppCompatActivity(R.layout.activity_shared_pref_listing) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharePref = intent.getStringExtra("shared_pref")

        val sharedPref = getSharedPreferences(sharePref, Context.MODE_PRIVATE)
        val sharedPrefs: List<Pair<String, Any?>> =
            sharedPref.all.map { it.key to it.value }.toList()

        val adapter = SharedPrefListingAdapter()
        adapter.setItemClickListener {
            showSharePrefInfo(it)
        }
        sharedPrefRV.adapter = adapter

        adapter.refresh(sharedPrefs)
    }

    private fun showSharePrefInfo(pair: Pair<String, Any?>){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SharePref Info")

        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_shared_pref_info, null)
        builder.setView(view)

        view.keyEt.setText(pair.first)
        pair.second?.let {
            view.valueEt.setText(it.toString())
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

}