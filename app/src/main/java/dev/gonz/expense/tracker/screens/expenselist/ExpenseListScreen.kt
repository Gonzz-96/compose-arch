package dev.gonz.expense.tracker.screens.expenselist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.gonz.expense.tracker.domain.model.Expense
import java.util.Date

@Composable
fun ExpenseListScreen(
    viewModel: ExpenseListViewModel = viewModel()
) {
    val viewState by viewModel.viewState.observeAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        when (viewState) {
            ExpenseListViewState.Loading -> {
                LoadingComposable()
            }
            is ExpenseListViewState.AllExpenses -> {
                val state = viewState as ExpenseListViewState.AllExpenses
                ExpenseList(expenses = state.expenses)
            }
            is ExpenseListViewState.Error -> {
                val error = viewState as ExpenseListViewState.Error
                ErrorView(error = error.error)
            }
        }
    }
}

@Composable
// TODO improve name
private fun LoadingComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
        Box(modifier = Modifier.height(10.dp))
        Text("Loading...")
    }
}

@Composable
private fun ExpenseList(expenses: List<Expense>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = expenses.size,
            key = { expenses[it].id },
        ) {
            ExpenseView(expenses[it])
        }
    }
}

@Composable
// TODO improve name
// TODO migrate to constraint layout
private fun ExpenseView(expense: Expense) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
            .padding(16.dp)
    ) {
        Text(expense.description, modifier = Modifier.align(Alignment.TopStart))
        Text(expense.quantity.toString(), modifier = Modifier.align(Alignment.BottomStart))
        Text(Date(expense.date).toString(), modifier = Modifier.align(Alignment.TopEnd))
    }
}

@Composable
// TODO improve name
private fun ErrorView(error: Throwable) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Something went wrong...")
        Box(modifier = Modifier.height(16.dp))
        Button(onClick = {  }) {
            Text("Try again!")
        }

    }
}
