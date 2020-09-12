package com.github.dhaval2404.sharedprefinspector.data.repository

import android.content.Context
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefDatabase
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedPrefRepository(context: Context) {

    private val mSharePrefDAO = SharedPrefDatabase.getInstance(context).sharePrefDAO()

    fun insert(sharedPref: SharedPref) {
        //TODO: Show Notification
        CoroutineScope(Dispatchers.Main).launch {
            mSharePrefDAO.insert(sharedPref)
        }
    }

    suspend fun getAll(sharedPref: SharedPref) {
        mSharePrefDAO.getAll()
    }

    suspend fun clear() {
        CoroutineScope(Dispatchers.Main).launch {
            mSharePrefDAO.clear()
        }
    }

}