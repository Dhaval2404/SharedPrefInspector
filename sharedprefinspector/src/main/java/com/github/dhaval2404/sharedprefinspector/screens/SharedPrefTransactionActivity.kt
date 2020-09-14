package com.github.dhaval2404.sharedprefinspector.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefFactory
import com.github.dhaval2404.sharedprefinspector.data.repository.SharedPrefRepository
import kotlinx.android.synthetic.main.activity_shared_pref_transaction.*

class SharedPrefTransactionActivity : AppCompatActivity(R.layout.activity_shared_pref_transaction) {

    companion object {
        var isInForeground: Boolean = false
    }

    private lateinit var mSharedPrefRepository: SharedPrefRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = SharedPrefTransactionAdapter()
        sharedPrefTransRV.adapter = adapter

        mSharedPrefRepository = SharedPrefFactory.getSharedPrefRepository(this)
        mSharedPrefRepository.getAll().observe(this, Observer {
            adapter.refresh(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_shared_pref_transactions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_clear ->{
                mSharedPrefRepository.clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}