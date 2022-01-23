package dev.gonz.expense.tracker.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDb : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
}