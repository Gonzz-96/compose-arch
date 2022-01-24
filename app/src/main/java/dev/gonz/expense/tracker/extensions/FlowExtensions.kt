package dev.gonz.expense.tracker.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <reified T> Flow<*>.castOrError(): Flow<T> {
    return map { item ->
        item as? T ?: throw UnexpectedResultException("")
    }
}

class UnexpectedResultException(message: String) : RuntimeException(message)