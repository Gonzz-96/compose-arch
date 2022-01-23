package dev.gonz.expense.tracker.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class ExpenseEntity(
    val description: String,
    val quantity: Double,
    val date: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)