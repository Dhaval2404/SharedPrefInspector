package com.github.dhaval2404.sharedprefinspector.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefDatabase
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import com.github.dhaval2404.sharedprefinspector.util.NotificationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface SharedPrefRepository {

    fun insert(sharedPref: SharedPref)

    fun getAll(): LiveData<List<SharedPref>>

    fun clear()

}

class SharedPrefRepositoryImpl(context: Context) : SharedPrefRepository {

    private val mSharePrefDAO = SharedPrefDatabase.getInstance(context).sharePrefDAO()
    private val mNotificationHelper = NotificationHelper(context)

    override fun insert(sharedPref: SharedPref) {
        CoroutineScope(Dispatchers.Main).launch {
            sharedPref.id = mSharePrefDAO.insert(sharedPref)
            mNotificationHelper.show(sharedPref)
        }
    }

    override fun getAll() = mSharePrefDAO.getAll()

    override fun clear() {
        CoroutineScope(Dispatchers.Main).launch {
            mSharePrefDAO.clear()
        }
    }

}