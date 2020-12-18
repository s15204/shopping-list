package com.example.shoppinglist.act

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.*
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemViewModel
import com.example.shoppinglist.data.ItemViewModelFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class ProductAddActivity : AppCompatActivity() {
    private lateinit var editProductName: EditText
    private lateinit var editProductPrice: EditText
    private lateinit var editProductAmount: EditText
    private val productViewModel: ItemViewModel by viewModels {
        ItemViewModelFactory((application as Main).repo)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_add)
        editProductName = findViewById(R.id.edit_new_product_name)
        editProductPrice = findViewById(R.id.edit_new_product_price)
        editProductAmount = findViewById(R.id.edit_new_product_amount)

        val button = findViewById<Button>(R.id.btn_add_product)
        button.setOnClickListener {
            val replyIntent = Intent()
            val newItem = Item(
                id = 0,
                name = editProductName.text.toString(),
                price = editProductPrice.text.toString().toDouble(),
                amount = editProductAmount.text.toString().toInt(),
                isBought = false
            )
            productViewModel.insert(newItem)
            setResult(Activity.RESULT_OK, replyIntent)
            broadcastOnItemAdded(newItem)
            finish()
        }
    }

    @OptIn(UnstableDefault::class)
    private fun broadcastOnItemAdded(item: Item) {
        val intent = Intent()
        intent.action = getString(R.string.action_item_added)
        intent.component = ComponentName("com.example.shoppinglist.broadcastreceiver","com.example.shoppinglist.broadcastreceiver.ItemReveiver")
        intent.setPackage("com.example.shoppinglist.broadcastreceiver")
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        intent.putExtra("item", Json.stringify(Item.serializer(), item))
        sendBroadcast(intent)
    }
}