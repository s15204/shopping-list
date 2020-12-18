package com.example.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Item (
    @PrimaryKey(autoGenerate = true) var id: Int,
    val name: String,
    val price: Double,
    val amount: Int,
    val isBought : Boolean
)