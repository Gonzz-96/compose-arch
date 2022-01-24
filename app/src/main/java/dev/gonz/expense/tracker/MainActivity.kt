package dev.gonz.expense.tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.gonz.expense.tracker.screens.expenselist.ExpenseListScreen
import dev.gonz.expense.tracker.ui.theme.ExpensetrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpensetrackerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ExpenseListScreen()
                }
            }
        }
    }
}
