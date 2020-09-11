package com.github.dhaval2404.sharedprefinspector.constant

import androidx.annotation.StringDef

@StringDef(Action.ADD, Action.UPDATE,
    Action.DELETE, Action.CLEAR)
@Retention(AnnotationRetention.SOURCE)
internal annotation class Action {
    companion object {
        const val ADD = "ADD"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val CLEAR = "CLEAR"
    }
}