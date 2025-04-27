package com.example.badbudget

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: String,
    val date: String,
    val description: String?,
    val receiptPath: String? = null // optional for future uploads
)
