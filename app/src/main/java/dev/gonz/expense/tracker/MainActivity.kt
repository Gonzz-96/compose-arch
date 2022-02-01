package dev.gonz.expense.tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import dagger.hilt.android.AndroidEntryPoint
import dev.gonz.expense.tracker.room.ExpenseDao
import dev.gonz.expense.tracker.room.ExpenseEntity
import dev.gonz.expense.tracker.screens.expenselist.ExpenseListScreen
import dev.gonz.expense.tracker.ui.theme.ExpensetrackerTheme
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var expensesDao: ExpenseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                expensesDao.deleteAll()
                flowOf(
                    ExpenseEntity("Fuel", 1_200.0, Date().time),
                    ExpenseEntity("Book", 300.0, Date().time),
                    ExpenseEntity("Laptop", 20_000.0, Date().time),
                    ExpenseEntity("Glasses", 1_500.0, Date().time),
                    ExpenseEntity("Snacks", 50.0, Date().time),
                ).onEach(expensesDao::insertExpense)
                    .launchIn(this)
            }
            ExpensetrackerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ExpenseListScreen()
                }
            }
        }
    }
}
