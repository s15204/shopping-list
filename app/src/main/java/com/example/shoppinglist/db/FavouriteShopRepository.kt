package com.example.shoppinglist.db

import androidx.annotation.WorkerThread
import com.example.shoppinglist.data.FavouriteShop
import kotlinx.coroutines.flow.Flow

class FavouriteShopRepository(private val shopDao: FavouriteShopDao) {
    var allShops: Flow<List<FavouriteShop>> = shopDao.getAll()

    @WorkerThread
    fun insert(shop: FavouriteShop) {
        shopDao.insert(shop)
    }

    fun delete(shop: FavouriteShop) {
        shopDao.delete(shop)
    }

    fun update(shop: FavouriteShop) {
        shopDao.update(shop)
    }
}