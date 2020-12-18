package com.example.shoppinglist.db

import androidx.room.*
import com.example.shoppinglist.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("select * from Item order by id")
    fun getAll() : Flow<List<Item>>

    @Query("delete from Item where ID = :id")
    fun deleteById(id : Int)

    @Insert
    fun insert(product : Item)

    @Delete
    fun delete(product : Item)

    @Update
    fun update(product: Item)
}