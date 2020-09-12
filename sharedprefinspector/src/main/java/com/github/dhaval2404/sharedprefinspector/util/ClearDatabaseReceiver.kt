package com.github.dhaval2404.sharedprefinspector.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ClearDatabaseReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intentt: Intent) {
        CoroutineScope(Dispatchers.IO).launch {
            SharedPrefFactory.getSharedPrefRepository(context).clear()
        }
        NotificationHelper.clearBuffer()
        NotificationHelper(context).clear()
    }

}