package com.example.badbudget.model

import androidx.room.*

@Dao
interface BudgetDao {

    @Insert
    suspend fun insertBudget(budget: Budget)

    @Update
    suspend fun updateBudget(budget: Budget)

    @Query("SELECT * FROM budgets ORDER BY category ASC")
    suspend fun getAllBudgets(): List<Budget>
}
