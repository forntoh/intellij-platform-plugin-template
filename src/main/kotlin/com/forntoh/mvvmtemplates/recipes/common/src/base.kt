package com.forntoh.mvvmtemplates.recipes.common.src

fun eventBus(
    packageName: String
) = """package $packageName

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Singleton

@Singleton
class EventBus {

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow()
    val events: SharedFlow<Event> = _events.asSharedFlow()

    suspend fun produceEvent(event: Event) {
        _events.emit(event)
    }

}
"""

fun event(
    packageName: String
) = """package $packageName

sealed class Event {
}
"""

