package com.example.shoppinglist.util

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.example.shoppinglist.R


class Preferences(private val context: Context) {
    private fun open(): SharedPreferences {
        return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    private fun edit(): Editor {
        return open().edit()
    }

    var welcomeText: String
        get() = open().getString("welcomeText", context.getString(R.string.welcome_text)) as String
        set(welcomeText){
            val edit = edit()
            edit.putString("welcomeText", welcomeText)
            edit.apply()
        }

    var imageState: Boolean
        get() = open().getBoolean("isImageTurnedOn", true)
        set(isImageTurnedOn) {
            val edit = edit()
            edit.putBoolean("isImageTurnedOn", isImageTurnedOn);
            edit.apply()
        }


}