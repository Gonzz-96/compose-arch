package dev.gonz.expense.tracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.gonz.expense.tracker.room.ExpenseDao
import dev.gonz.expense.tracker.room.ExpenseDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): ExpenseDb {
        return Room.databaseBuilder(context, ExpenseDb::class.java, "expense.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideExpenseDao(expenseDb: ExpenseDb): ExpenseDao {
        return expenseDb.expenseDao()
    }
}