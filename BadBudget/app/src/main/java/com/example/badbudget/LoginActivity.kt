package com.example.badbudget

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var checkBoxRememberMe: CheckBox
    private lateinit var buttonLogin: Button
    private lateinit var textViewForgotPassword: TextView
    private lateinit var textViewCreateAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Views
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        checkBoxRememberMe = findViewById(R.id.checkboxRememberMe)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword)
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount)

        // Login button click
        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                // For now, fake login - we'll connect to database later
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                // Go to Dashboard screen (to be built next)
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Forgot password clicked
        textViewForgotPassword.setOnClickListener {
            Toast.makeText(this, "Password reset link sent!", Toast.LENGTH_SHORT).show()
        }

        // Create new account clicked
        textViewCreateAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
