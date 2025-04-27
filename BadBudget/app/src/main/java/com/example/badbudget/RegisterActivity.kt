package com.example.badbudget

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextUsernameRegister: EditText
    private lateinit var editTextEmailRegister: EditText
    private lateinit var editTextPasswordRegister: EditText
    private lateinit var editTextConfirmPasswordRegister: EditText
    private lateinit var buttonCreateAccount: Button
    private lateinit var textViewBackToLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Views
        editTextUsernameRegister = findViewById(R.id.editTextUsernameRegister)
        editTextEmailRegister = findViewById(R.id.editTextEmailRegister)
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister)
        editTextConfirmPasswordRegister = findViewById(R.id.editTextConfirmPasswordRegister)
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount)
        textViewBackToLogin = findViewById(R.id.textViewBackToLogin)

        // Create Account Button
        buttonCreateAccount.setOnClickListener {
            val username = editTextUsernameRegister.text.toString().trim()
            val email = editTextEmailRegister.text.toString().trim()
            val password = editTextPasswordRegister.text.toString().trim()
            val confirmPassword = editTextConfirmPasswordRegister.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            } else {
                // For now, show success message
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()

                // Return to Login screen
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Back to Login link
        textViewBackToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
