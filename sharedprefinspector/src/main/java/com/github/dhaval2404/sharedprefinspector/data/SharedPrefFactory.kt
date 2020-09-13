package com.github.dhaval2404.sharedprefinspector.data

import android.content.Context
import com.github.dhaval2404.sharedprefinspector.data.repository.SharedPrefRepository
import com.github.dhaval2404.sharedprefinspector.data.repository.SharedPrefRepositoryImpl

object SharedPrefFactory {

    private lateinit var mSharedPrefRepository: SharedPrefRepository

    fun getSharedPrefRepository(context: Context): SharedPrefRepository {
        if (!::mSharedPrefRepository.isInitialized) {
            mSharedPrefRepository = SharedPrefRepositoryImpl(context)
        }
        return mSharedPrefRepository
    }

}