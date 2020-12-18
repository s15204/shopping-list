package com.example.shoppinglist.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shoppinglist.R
import com.example.shoppinglist.util.Preferences
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private val preferences = Preferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings);

        switchImage.isChecked = preferences.imageState;
        editWelcomeText.setText(preferences.welcomeText)

        btnSaveSettings.setOnClickListener{
            preferences.welcomeText = editWelcomeText.text.toString()
            preferences.imageState = switchImage.isChecked
            Toast.makeText(this, "Settings saved!", Toast.LENGTH_SHORT).show()
        }
    }
}