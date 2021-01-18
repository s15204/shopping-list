package com.example.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
class FavouriteShop (
    @PrimaryKey(autoGenerate = true) var id: String,
    val name: String,
    val description: String,
    val X : Double,
    val Y: Double,
    val radius: Int
)