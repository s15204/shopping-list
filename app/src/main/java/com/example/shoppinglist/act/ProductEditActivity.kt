package com.example.shoppinglist.act

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.Main
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemViewModel
import com.example.shoppinglist.data.ItemViewModelFactory
import kotlinx.serialization.json.Json

class ProductEditActivity : AppCompatActivity() {
    private lateinit var editProductName: EditText
    private lateinit var editProductPrice: EditText
    private lateinit var editProductAmount: EditText

    private val productViewModel: ItemViewModel by viewModels {
        ItemViewModelFactory((application as Main).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_edit)
        setSupportActionBar(findViewById(R.id.toolbar))

        editProductName = findViewById(R.id.edit_product_name)
        editProductPrice = findViewById(R.id.edit_product_price)
        editProductAmount = findViewById(R.id.edit_product_amount)

        productViewModel.getHeld()?.let { it1 ->
            editProductName.setText(it1.name)
            editProductPrice.setText(it1.price.toString())
            editProductAmount.setText(it1.amount.toString())
        }

        val intentItem = intent.getStringExtra("item")
        if (intentItem != null) {
            val item: Item = Json.parse(Item.serializer(), intentItem)
            editProductName.setText(item.name)
            editProductPrice.setText(item.price.toString())
            editProductAmount.setText(item.amount.toString())
        }

        val edit = findViewById<Button>(R.id.btn_edit_product)
        val delete = findViewById<Button>(R.id.btn_delete_product)

        edit.setOnClickListener {
            productViewModel.getHeld()?.let { it1 ->
                val edited = Item(
                    id = it1.id,
                    name = editProductName.text.toString(),
                    price = editProductPrice.text.toString().toDouble(),
                    amount = editProductAmount.text.toString().toInt(),
                    isBought = it1.isBought
                )
                productViewModel.delete(it1)
                productViewModel.insert(edited)
            }
            finish()
        }

        delete.setOnClickListener {
            productViewModel.getHeld()?.let { it1 -> productViewModel.delete(it1) }
            finish()
        }
    }
}