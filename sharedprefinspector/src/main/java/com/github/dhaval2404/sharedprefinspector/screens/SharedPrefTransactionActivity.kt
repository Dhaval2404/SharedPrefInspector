package com.github.dhaval2404.sharedprefinspector.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefFactory
import kotlinx.android.synthetic.main.activity_shared_pref_transaction.*

class SharedPrefTransactionActivity : AppCompatActivity(R.layout.activity_shared_pref_transaction) {

    companion object {
        var isInForeground: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = SharedPrefTransactionAdapter()
        sharedPrefTransRV.adapter = adapter

        val repository = SharedPrefFactory.getSharedPrefRepository(this)
        repository.getAll().observe(this, Observer {
            adapter.refresh(it)
        })

    }

}