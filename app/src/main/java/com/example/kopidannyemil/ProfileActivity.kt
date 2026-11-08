
package com.example.kopidannyemil

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvName = findViewById<TextView>(R.id.tvProfileName)
        val tvEmail = findViewById<TextView>(R.id.tvProfileEmail)
        val btnBack = findViewById<Button>(R.id.btnProfileBack)

        tvName.text = AccountStorage.getName(this)
        tvEmail.text = AccountStorage.getEmail(this)

        btnBack.setOnClickListener {
            finish()
        }
    }
}
