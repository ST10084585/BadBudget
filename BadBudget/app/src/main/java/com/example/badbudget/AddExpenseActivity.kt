package com.example.badbudget

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var editTextAmount: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var buttonSelectDate: Button
    private lateinit var textViewSelectedDate: TextView
    private lateinit var editTextDescription: EditText
    private lateinit var buttonUploadReceipt: Button
    private lateinit var buttonLogExpense: Button

    private var selectedDate: String = ""

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Initialize Views
        editTextAmount = findViewById(R.id.editTextAmount)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        buttonSelectDate = findViewById(R.id.buttonSelectDate)
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        editTextDescription = findViewById(R.id.editTextDescription)
        buttonUploadReceipt = findViewById(R.id.buttonUploadReceipt)
        buttonLogExpense = findViewById(R.id.buttonLogExpense)

        // Setup Spinner
        val categories = arrayOf("Food", "Transport", "Entertainment", "Bills", "Shopping", "Other")
        spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        // Setup database
        db = AppDatabase.getDatabase(this)

        // Select Date
        buttonSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                selectedDate = sdf.format(selectedCalendar.time)
                textViewSelectedDate.text = selectedDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        // Upload Receipt Button (future)
        buttonUploadReceipt.setOnClickListener {
            Toast.makeText(this, "Upload feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Log Expense Button
        buttonLogExpense.setOnClickListener {
            val amountText = editTextAmount.text.toString().trim()
            val category = spinnerCategory.selectedItem.toString()
            val description = editTextDescription.text.toString().trim()

            if (amountText.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show()
            } else {
                val amount = amountText.toDouble()

                val newExpense = Expense(
                    amount = amount,
                    category = category,
                    date = selectedDate,
                    description = description,
                    receiptPath = null
                )

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        db.expenseDao().insertExpense(newExpense)
                    }
                    Toast.makeText(this@AddExpenseActivity, "Expense logged!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
