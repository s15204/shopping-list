package com.example.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.Item
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Item::class], version = 3, exportSchema = false )
abstract class ItemDatabase : RoomDatabase() {
    abstract  fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var db: ItemDatabase? = null

        fun getDatabase(context: Context) : ItemDatabase {
            return db ?: synchronized(this) {
                val builder = Room.databaseBuilder(context.applicationContext, ItemDatabase::class.java, "item db")
                val newDb = builder.allowMainThreadQueries().fallbackToDestructiveMigration().build()
                db = newDb
                return newDb
            }
        }
    }
}