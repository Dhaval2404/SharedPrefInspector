package com.github.dhaval2404.sharedprefinspector.screens.shared_pref_file_listing

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.sharedprefinspector.R
import com.github.dhaval2404.sharedprefinspector.screens.share_pref_listing.SharedPrefListingActivity
import kotlinx.android.synthetic.main.activity_shared_pref_file_list.*
import java.io.File

class SharedPrefFilesListingActivity : AppCompatActivity(R.layout.activity_shared_pref_file_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = SharedPrefFilesListingAdapter()
        adapter.setItemClickListener {
            startActivity(Intent(this, SharedPrefListingActivity::class.java).apply {
                putExtra("shared_pref", it.nameWithoutExtension)
            })
        }
        sharedPrefFileRV.adapter = adapter

        val prefDirs = File(applicationInfo.dataDir, "shared_prefs");

        if (prefDirs.exists() && prefDirs.isDirectory) {
            prefDirs.listFiles()?.toList()?.let {
                adapter.refresh(it)
            }
        }
    }

}