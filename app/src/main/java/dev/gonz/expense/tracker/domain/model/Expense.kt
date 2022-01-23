package dev.gonz.expense.tracker.domain.model

import dev.gonz.expense.tracker.room.ExpenseEntity

data class Expense(
    val id: Long,
    val description: String,
    val quantity: Double,
    val date: Long,
) {

    companion object {
        fun ExpenseEntity.toExpense() = Expense(
            id = this.id,
            description = this.description,
            quantity = this.quantity,
            date = this.date,
        )
    }
}
