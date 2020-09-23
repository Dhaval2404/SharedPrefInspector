package com.github.dhaval2404.sharedprefinspector.screens.share_pref_listing

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.sharedprefinspector.R
import kotlinx.android.synthetic.main.activity_shared_pref_listing.*

class SharedPrefListingActivity : AppCompatActivity(R.layout.activity_shared_pref_listing) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharePref = intent.getStringExtra("shared_pref")

        val sharedPref = getSharedPreferences(sharePref, Context.MODE_PRIVATE)
        val sharedPrefs:List<Pair<String, Any?>> = sharedPref.all.map { it.key to it.value }.toList()

        val adapter = SharedPrefListingAdapter()
        sharedPrefRV.adapter = adapter

        adapter.refresh(sharedPrefs)
    }

}