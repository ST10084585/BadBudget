package com.example.badbudget

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.badbudget.model.AppDatabase
import com.example.badbudget.model.Budget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BudgetsActivity : AppCompatActivity() {

    private lateinit var recyclerViewBudgets: RecyclerView
    private lateinit var editTextBudgetCategory: EditText
    private lateinit var editTextBudgetLimit: EditText
    private lateinit var buttonAddBudget: Button
    private lateinit var adapter: BudgetAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budgets)

        recyclerViewBudgets = findViewById(R.id.recyclerViewBudgets)
        editTextBudgetCategory = findViewById(R.id.editTextBudgetCategory)
        editTextBudgetLimit = findViewById(R.id.editTextBudgetLimit)
        buttonAddBudget = findViewById(R.id.buttonAddBudget)

        adapter = BudgetAdapter(emptyList())
        recyclerViewBudgets.layoutManager = LinearLayoutManager(this)
        recyclerViewBudgets.adapter = adapter

        db = AppDatabase.getDatabase(this)

        loadBudgets()

        buttonAddBudget.setOnClickListener {
            val category = editTextBudgetCategory.text.toString().trim()
            val limitText = editTextBudgetLimit.text.toString().trim()

            if (category.isEmpty() || limitText.isEmpty()) {
                Toast.makeText(this, "Fill in all fields!", Toast.LENGTH_SHORT).show()
            } else {
                val limit = limitText.toDouble()
                val newBudget = Budget(category = category, limitAmount = limit)

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        db.budgetDao().insertBudget(newBudget)
                    }
                    Toast.makeText(this@BudgetsActivity, "Budget Added!", Toast.LENGTH_SHORT).show()
                    loadBudgets()
                    editTextBudgetCategory.text.clear()
                    editTextBudgetLimit.text.clear()
                }
            }
        }
    }

    private fun loadBudgets() {
        lifecycleScope.launch {
            val budgets = withContext(Dispatchers.IO) {
                db.budgetDao().getAllBudgets()
            }
            adapter.setBudgets(budgets)
        }
    }
}
