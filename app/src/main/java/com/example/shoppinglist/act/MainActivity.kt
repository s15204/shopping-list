package com.example.shoppinglist.act

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R
import com.example.shoppinglist.util.Preferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val preferences = Preferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenSettings.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java);
            startActivity(settingsIntent);
        }

        btnOpenShoppingList.setOnClickListener {
            val shoppingListIntent = Intent(this, ProductListActivity::class.java)
            startActivity(shoppingListIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        txtHomeTitle.text = preferences.welcomeText
        if(!preferences.imageState) {
            image.visibility = View.INVISIBLE;
        } else {
            image.visibility = View.VISIBLE;
        }
    }
}
