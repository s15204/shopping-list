package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.db.ItemDatabase
import com.example.shoppinglist.db.ItemRepository

class Main : Application() {
    private val db by lazy { ItemDatabase.getDatabase(this) }
    val repo by lazy { ItemRepository(db.itemDao()) }
}