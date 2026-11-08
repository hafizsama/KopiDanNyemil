
package com.example.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvGoRegister)

        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            when {
                email.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Email dan password harus diisi!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                AccountStorage.checkAccount(this, email, password) -> {
                    Toast.makeText(
                        this,
                        "Login berhasil! Selamat datang.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    finish()
                }

                else -> {
                    Toast.makeText(
                        this,
                        "Email atau password salah!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
