package com.github.dhaval2404.sharedprefinspector.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.dhaval2404.sharedprefinspector.constant.Action
import java.util.Date

@Entity(tableName = "shared_pref")
data class SharedPref(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @Action
    @ColumnInfo(name = "action")
    var action: String = Action.ADD,
    @ColumnInfo(name = "key")
    var key: String = "",
    @ColumnInfo(name = "value")
    var value: String? = null,
    @ColumnInfo(name = "timestamp")
    var date: Date = Date()
)
