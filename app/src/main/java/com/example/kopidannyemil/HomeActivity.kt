
package com.example.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Tombol menuju halaman profil
        val btnProfile = findViewById<Button>(R.id.btnProfile)

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Tombol pemesanan menu
        val orderButtons = listOf(
            R.id.btnOrder1,
            R.id.btnOrder2,
            R.id.btnOrder3,
            R.id.btnOrder4,
            R.id.btnOrder5
        )

        val menuNames = listOf(
            "Kopi Susu Gula Aren",
            "Cappuccino",
            "Es Cokelat",
            "Roti Bakar Cokelat",
            "Kentang Goreng"
        )

        orderButtons.forEachIndexed { index, buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                val intent = Intent(
                    this,
                    ShippingAddressActivity::class.java
                )

                intent.putExtra("productName", menuNames[index])
                startActivity(intent)
            }
        }
    }
}
