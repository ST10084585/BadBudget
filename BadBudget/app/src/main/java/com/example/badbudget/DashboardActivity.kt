package com.example.badbudget

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var textViewWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        textViewWelcome = findViewById(R.id.textViewWelcome)

        // Later we fetch username from database/login
        textViewWelcome.text = "Welcome, User!"
    }
}
