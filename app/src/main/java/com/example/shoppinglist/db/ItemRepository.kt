package com.example.shoppinglist.db

import androidx.annotation.WorkerThread
import com.example.shoppinglist.data.Item
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    var held: Item? = null
    val allItems: Flow<List<Item>> = itemDao.getAll()

    @WorkerThread
    fun insert(item : Item) {
        itemDao.insert(item)
    }

    fun delete(item : Item) {
        itemDao.delete(item)
    }

    fun update(item: Item) {
        itemDao.update(item)
    }

    fun hold(item: Item) {
        held = item
    }

}