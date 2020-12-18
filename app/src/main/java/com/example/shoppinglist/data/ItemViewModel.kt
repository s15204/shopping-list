package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.db.ItemRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemViewModel(private val repository : ItemRepository) : ViewModel() {
    val allItems: LiveData<List<Item>> = repository.allItems.asLiveData()

    fun insert(item: Item) = viewModelScope.launch { repository.insert(item) }
    fun delete(item: Item) = viewModelScope.launch { repository.delete(item) }
    fun update(item: Item) = viewModelScope.launch { repository.update(item) }
    fun hold(item: Item) = viewModelScope.launch { repository.hold(item) }
    fun getHeld(): Item? = repository.held
}