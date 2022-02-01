package dev.gonz.expense.tracker.screens.expenselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gonz.expense.tracker.domain.model.Expense
import dev.gonz.expense.tracker.extensions.castOrError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val expenseListRepo: ExpenseListRepo,
) : ViewModel() {

    private val _viewState = MutableLiveData<ExpenseListViewState>(ExpenseListViewState.Loading)
    val viewState: LiveData<ExpenseListViewState>
        get() = _viewState

    init {
        fetchExpenses()
    }

    private fun fetchExpenses() {
        expenseListRepo.query(ExpenseListSpec.GetAllExpenses)
            .castOrError<ExpenseListResult.AllExpenses>()
            .onEach {
                delay(2_000L) // TODO simulate expensive operation
                _viewState.value = ExpenseListViewState.AllExpenses(it.expenses)
            }
            .catch { _viewState.value = ExpenseListViewState.Error(it) }
            .launchIn(viewModelScope)
    }

}

sealed class ExpenseListViewState {
    object Loading : ExpenseListViewState()

    data class AllExpenses(val expenses: List<Expense>) : ExpenseListViewState()
    data class Error(val error: Throwable) : ExpenseListViewState()
}