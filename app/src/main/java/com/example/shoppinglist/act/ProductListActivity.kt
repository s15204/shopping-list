package com.example.shoppinglist.act

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.*
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemListAdapter
import com.example.shoppinglist.data.ItemViewModel
import com.example.shoppinglist.data.ItemViewModelFactory
import com.example.shoppinglist.util.IClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProductListActivity : AppCompatActivity(), IClickListener {
    private val newProductActivityRequestCode = 1
    private val editProductActivityRequestCode = 2
    private val productViewModel: ItemViewModel by viewModels {
        ItemViewModelFactory((application as Main).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        val recyclerView = findViewById<RecyclerView>(R.id.item_recycler_view)
        val adapter = ItemListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        productViewModel.allItems.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this@ProductListActivity, ProductAddActivity::class.java)
            startActivityForResult(intent, newProductActivityRequestCode)
        }
    }

    override fun onItemClicked(item: Item) {
        productViewModel.hold(item)
        val intent = Intent(this@ProductListActivity, ProductEditActivity::class.java)
        startActivityForResult(intent, editProductActivityRequestCode)
    }
}