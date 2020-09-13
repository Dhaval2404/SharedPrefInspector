package com.github.dhaval2404.sharedprefinspector.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref

/**
 * SharePref DAO class
 *
 * @author Dhaval Patel
 * @version 1.0
 * @since 10 September 2020
 */
@Dao
interface SharePrefDAO {

    @Insert
    suspend fun insert(sharedPref: SharedPref): Long

    @Query("select * from shared_pref order by timestamp desc")
    fun getAll(): LiveData<List<SharedPref>>

    @Query("delete from shared_pref")
    suspend fun clear()

}