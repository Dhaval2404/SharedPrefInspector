package com.github.dhaval2404.sharedprefinspector.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.dhaval2404.sharedprefinspector.data.converters.DateConverter
import com.github.dhaval2404.sharedprefinspector.data.dao.SharePrefDAO
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref


/**
 * Application Database Class
 *
 * @author Rahul Upadhyay
 * @version 1.0
 * @since 06 August 2018
 */
@Database(entities = [SharedPref::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class SharedPrefDatabase : RoomDatabase() {

    abstract fun sharePrefDAO(): SharePrefDAO

    companion object {

        fun getInstance(context: Context): SharedPrefDatabase {
            return Room.databaseBuilder(context, SharedPrefDatabase::class.java, "shared_pref_inspector.db")
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}