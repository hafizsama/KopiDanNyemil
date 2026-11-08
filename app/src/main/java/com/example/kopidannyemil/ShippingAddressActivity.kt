
package com.example.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShippingAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)

        val etRecipient = findViewById<EditText>(R.id.etRecipient)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etCity = findViewById<EditText>(R.id.etCity)
        val btnSave = findViewById<Button>(R.id.btnSaveAddress)

        // Mengambil nama produk dari halaman Home
        val productName = intent.getStringExtra("productName") ?: "-"

        btnSave.setOnClickListener {
            val recipient = etRecipient.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val city = etCity.text.toString().trim()

            when {
                recipient.isEmpty() ||
                        phone.isEmpty() ||
                        address.isEmpty() ||
                        city.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Semua kolom alamat harus diisi!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val intent = Intent(
                        this,
                        OrderConfirmationActivity::class.java
                    )

                    intent.putExtra("productName", productName)
                    intent.putExtra("recipient", recipient)
                    intent.putExtra("phone", phone)
                    intent.putExtra("address", address)
                    intent.putExtra("city", city)

                    startActivity(intent)
                }
            }
        }
    }
}
