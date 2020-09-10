package com.github.dhaval2404.sharedprefinspector.data

import android.content.Context
import com.github.dhaval2404.sharedprefinspector.data.repository.SharedPrefRepository

object SharedPrefFactory {

    private lateinit var mSharedPrefRepository: SharedPrefRepository

    fun getSharedPrefRepository(context: Context): SharedPrefRepository {
        if (!::mSharedPrefRepository.isInitialized) {
            mSharedPrefRepository = SharedPrefRepository(context)
        }
        return mSharedPrefRepository
    }

}