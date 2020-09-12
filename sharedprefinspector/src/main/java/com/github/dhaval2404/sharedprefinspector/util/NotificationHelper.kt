package com.github.dhaval2404.sharedprefinspector.util;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.LongSparseArray
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import com.github.dhaval2404.sharedprefinspector.screens.SharedPrefTransactionActivity
import java.util.HashSet

internal class NotificationHelper(val context: Context) {

    companion object {
        private const val TRANSACTIONS_CHANNEL_ID = "shared_pref_transactions"
        private const val TRANSACTION_NOTIFICATION_ID = 1010

        private const val BUFFER_SIZE = 10
        private const val INTENT_REQUEST_CODE = 11
        private val transactionBuffer = LongSparseArray<SharedPref>()
        private val transactionIdsSet = HashSet<Long>()

        fun clearBuffer() {
            synchronized(transactionBuffer) {
                transactionBuffer.clear()
                transactionIdsSet.clear()
            }
        }
    }

    private val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val transactionsScreenIntent by lazy {
        PendingIntent.getActivity(
            context,
            TRANSACTION_NOTIFICATION_ID,
            getLaunchIntent(context),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }


    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val transactionsChannel = NotificationChannel(
                TRANSACTIONS_CHANNEL_ID,
                "SharePref Transaction",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(transactionsChannel)
        }
    }

    private fun addToBuffer(sharedPref: SharedPref) {
        if (sharedPref.id == 0L) {
            // Don't store Transactions with an invalid ID (0).
            // Transaction with an Invalid ID will be shown twice in the notification
            // with both the invalid and the valid ID and we want to avoid this.
            return
        }
        synchronized(transactionBuffer) {
            transactionIdsSet.add(sharedPref.id)
            transactionBuffer.put(sharedPref.id, sharedPref)
            if (transactionBuffer.size() > BUFFER_SIZE) {
                transactionBuffer.removeAt(0)
            }
        }
    }

    fun show(sharedPref: SharedPref) {
        addToBuffer(sharedPref)
        if (!SharedPrefTransactionActivity.isInForeground) {
            val builder =
                NotificationCompat.Builder(context, TRANSACTIONS_CHANNEL_ID)
                    .setContentIntent(transactionsScreenIntent)
                    .setLocalOnly(true)
                    .setSmallIcon(R.drawable.outline_notifications_black_24dp)
                    .setColor(ContextCompat.getColor(context, R.color.sharedpref_color_primary))
                    .setContentTitle("SharedPref Transactions")
                    .setAutoCancel(true)
                    .addAction(getClearAction())
            val inboxStyle = NotificationCompat.InboxStyle()
            synchronized(transactionBuffer) {
                var count = 0
                (transactionBuffer.size() - 1 downTo 0).forEach { i ->
                    val bufferedTransaction = transactionBuffer.valueAt(i)
                    if ((bufferedTransaction != null) && count < BUFFER_SIZE) {
                        if (count == 0) {
                            builder.setContentText(bufferedTransaction.getNotificationText())
                        }
                        inboxStyle.addLine(bufferedTransaction.getNotificationText())
                    }
                    count++
                }
                builder.setStyle(inboxStyle)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    builder.setSubText(transactionIdsSet.size.toString())
                } else {
                    builder.setNumber(transactionIdsSet.size)
                }
            }
            notificationManager.notify(TRANSACTION_NOTIFICATION_ID, builder.build())
        }
    }

    private fun getClearAction():
            NotificationCompat.Action {
        val clearTitle = "Clear"
        val deleteIntent = Intent(context, ClearDatabaseReceiver::class.java)
        val intent = PendingIntent.getBroadcast(
            context,
            INTENT_REQUEST_CODE,
            deleteIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
        return NotificationCompat.Action(R.drawable.outline_delete_black_24dp, clearTitle, intent)
    }

    fun clear() {
        notificationManager.cancel(TRANSACTION_NOTIFICATION_ID)
    }

    private fun getLaunchIntent(context: Context): Intent {
        return Intent(context, SharedPrefTransactionActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

}