package com.example.shoppinglist.db

import androidx.room.*
import com.example.shoppinglist.data.FavouriteShop
import com.example.shoppinglist.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteShopDao {

    @Query("select * from FavouriteShop")
    fun getAll() : Flow<List<FavouriteShop>>

    @Query("delete from FavouriteShop where ID = :id")
    fun deleteById(id : String)

    @Insert
    fun insert(shop : FavouriteShop)

    @Delete
    fun delete(shop : FavouriteShop)

    @Update
    fun update(shop: FavouriteShop)
}