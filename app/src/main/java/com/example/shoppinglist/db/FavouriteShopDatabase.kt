package com.example.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.Item

@Database(entities = [Item::class], version = 1, exportSchema = false )
abstract class FavouriteShopDatabase : RoomDatabase() {
    abstract fun favouriteShopDao() : FavouriteShopDao

    companion object {
        @Volatile
        private var db: FavouriteShopDatabase? = null

        fun getDatabase(context: Context) : FavouriteShopDatabase {
            return db ?: synchronized(this) {
                val builder = Room.databaseBuilder(context.applicationContext, FavouriteShopDatabase::class.java, "fav shop db")
                val newDb = builder.allowMainThreadQueries().fallbackToDestructiveMigration().build()
                db = newDb
                return newDb
            }
        }
    }

}