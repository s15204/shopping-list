package com.example.shoppinglist.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.db.ItemRepository
import java.lang.IllegalArgumentException

class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java))
            return ItemViewModel(repository) as T
        throw IllegalArgumentException(String.format("No %s class present", modelClass.toString()))
    }
}