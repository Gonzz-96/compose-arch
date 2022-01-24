package dev.gonz.expense.tracker.screens.expenselist

import dagger.hilt.android.scopes.ActivityRetainedScoped
import dev.gonz.expense.tracker.domain.model.Expense
import dev.gonz.expense.tracker.domain.model.Expense.Companion.toExpense
import dev.gonz.expense.tracker.room.ExpenseDao
import dev.gonz.expense.tracker.screens.expenselist.ExpenseListResult.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ActivityRetainedScoped
class ExpenseListRepo @Inject constructor(
    private val expenseDao: ExpenseDao,
) {

    fun query(spec: ExpenseListSpec): Flow<ExpenseListResult> {
        return when (spec) {
            ExpenseListSpec.GetAllExpenses -> getExpenseFlow()
        }
    }

    private fun getExpenseFlow(): Flow<ExpenseListResult> {
        return expenseDao.getAllExpenses()
            .map {
                it.map { expenseEntity ->
                    expenseEntity.toExpense()
                }
            }
            .map { AllExpenses(it) }
            .catch<ExpenseListResult> { emit(Error(it)) }
    }
}

sealed class ExpenseListSpec {
    object GetAllExpenses : ExpenseListSpec()
}

sealed class ExpenseListResult {
    data class AllExpenses(val expenses: List<Expense>) : ExpenseListResult()
    data class Error(val error: Throwable) : ExpenseListResult()
}