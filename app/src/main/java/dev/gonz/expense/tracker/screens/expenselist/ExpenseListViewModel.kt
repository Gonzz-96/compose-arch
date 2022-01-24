package dev.gonz.expense.tracker.screens.expenselist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gonz.expense.tracker.domain.model.Expense
import dev.gonz.expense.tracker.extensions.castOrError
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val expenseListRepo: ExpenseListRepo,
): ViewModel() {

    private val _viewState = MutableLiveData<ExpenseListViewState>(ExpenseListViewState.Loading)
    val viewState: LiveData<ExpenseListViewState>
        get() = _viewState

    init {
        fetchExpenses()
    }

    private fun fetchExpenses() {
        expenseListRepo.query(ExpenseListSpec.GetAllExpenses)
            .castOrError<ExpenseListResult.AllExpenses>()
            .map { _viewState.value = ExpenseListViewState.Expenses(it.expenses) }
            .launchIn(viewModelScope)
    }

}

sealed class ExpenseListViewState {
    object Loading : ExpenseListViewState()

    data class Expenses(val expenses: List<Expense>) : ExpenseListViewState()
}