package dev.gonz.expense.tracker.screens.expenselist

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExpenseListScreen(
    viewModel: ExpenseListViewModel = viewModel()
) {
    val viewState by viewModel.viewState.observeAsState()

    Log.v("FROM EXPENSE LIST", (viewState as? ExpenseListViewState.Expenses)?.expenses.toString())
}