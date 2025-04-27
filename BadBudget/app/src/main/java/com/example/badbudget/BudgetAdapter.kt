package com.example.badbudget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.badbudget.model.Budget

class BudgetAdapter(private var budgets: List<Budget>) :
    RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

    inner class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val textViewLimit: TextView = itemView.findViewById(R.id.textViewLimit)
        val textViewSpent: TextView = itemView.findViewById(R.id.textViewSpent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_budget, parent, false)
        return BudgetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val budget = budgets[position]
        holder.textViewCategory.text = "Category: ${budget.category}"
        holder.textViewLimit.text = "Limit: R${budget.limitAmount}"
        holder.textViewSpent.text = "Spent: R${budget.spentAmount}"
    }

    override fun getItemCount(): Int {
        return budgets.size
    }

    fun setBudgets(newBudgets: List<Budget>) {
        budgets = newBudgets
        notifyDataSetChanged()
    }
}
