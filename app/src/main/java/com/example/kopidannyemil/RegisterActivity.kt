
package com.example.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.util.UUID

object AccountStorage {

    private const val PREF_NAME = "KopiNyemilAccount"

    fun saveAccount(
        activity: AppCompatActivity,
        name: String,
        email: String,
        password: String
    ) {
        val salt = UUID.randomUUID().toString()
        val passwordHash = hashPassword(password, salt)

        val preferences = activity.getSharedPreferences(
            PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )

        preferences.edit()
            .putString("name", name)
            .putString("email", email.trim().lowercase())
            .putString("salt", salt)
            .putString("passwordHash", passwordHash)
            .apply()
    }

    fun checkAccount(
        activity: AppCompatActivity,
        email: String,
        password: String
    ): Boolean {
        val preferences = activity.getSharedPreferences(
            PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )

        val savedEmail = preferences.getString("email", null)
        val savedSalt = preferences.getString("salt", null)
        val savedHash = preferences.getString("passwordHash", null)

        if (savedEmail == null || savedSalt == null || savedHash == null) {
            return false
        }

        val enteredHash = hashPassword(password, savedSalt)

        return email.trim().lowercase() == savedEmail &&
                enteredHash == savedHash
    }

    fun emailExists(
        activity: AppCompatActivity,
        email: String
    ): Boolean {
        val preferences = activity.getSharedPreferences(
            PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )

        return preferences.getString("email", null) ==
                email.trim().lowercase()
    }

    fun getName(activity: AppCompatActivity): String {
        val preferences = activity.getSharedPreferences(
            PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )

        return preferences.getString("name", "Pengguna") ?: "Pengguna"
    }

    fun getEmail(activity: AppCompatActivity): String {
        val preferences = activity.getSharedPreferences(
            PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )

        return preferences.getString("email", "-") ?: "-"
    }

    private fun hashPassword(password: String, salt: String): String {
        val bytes = MessageDigest.getInstance("SHA-256")
            .digest((salt + password).toByteArray())

        return bytes.joinToString("") { "%02x".format(it) }
    }
}

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnCreate = findViewById<Button>(R.id.btnCreateAccount)

        btnCreate.setOnClickListener {

            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            when {
                name.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                    showMessage("Semua kolom harus diisi!")
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showMessage("Format email tidak valid!")
                }

                password.length < 6 -> {
                    showMessage("Password minimal 6 karakter!")
                }

                AccountStorage.emailExists(this, email) -> {
                    showMessage("Email sudah terdaftar!")
                }

                else -> {
                    AccountStorage.saveAccount(
                        this,
                        name,
                        email,
                        password
                    )

                    Toast.makeText(
                        this,
                        "Pendaftaran berhasil! Silakan login.",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
