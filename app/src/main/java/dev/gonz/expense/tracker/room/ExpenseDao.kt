package dev.gonz.expense.tracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): Flow<ExpenseEntity>

    @Query("DELETE FROM expense WHERE id = :id")
    suspend fun deleteExpenseById(id: Long)

    @Query("DELETE FROM expense")
    suspend fun deleteAll()
}