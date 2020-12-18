package com.example.shoppinglist.util

import com.example.shoppinglist.data.Item

interface IClickListener {
    fun onItemClicked(item: Item)
}