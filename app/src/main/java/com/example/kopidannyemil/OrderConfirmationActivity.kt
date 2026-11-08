
package com.example.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val tvProduct = findViewById<TextView>(R.id.tvProduct)
        val tvRecipient = findViewById<TextView>(R.id.tvRecipient)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val btnFinish = findViewById<Button>(R.id.btnFinish)

        val productName = intent.getStringExtra("productName") ?: "-"
        val recipient = intent.getStringExtra("recipient") ?: "-"
        val phone = intent.getStringExtra("phone") ?: "-"
        val address = intent.getStringExtra("address") ?: "-"
        val city = intent.getStringExtra("city") ?: "-"

        tvProduct.text = productName
        tvRecipient.text = recipient
        tvPhone.text = phone
        tvAddress.text = "$address, $city"

        btnFinish.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
            finish()
        }
    }
}
