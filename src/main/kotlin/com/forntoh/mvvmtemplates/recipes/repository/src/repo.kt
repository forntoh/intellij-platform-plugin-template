package com.forntoh.mvvmtemplates.recipes.repository.src

fun baseRepository(
    packageName: String,
    commonPackageName: String,
    webServicePackageName: String
): String = """package $packageName

import $commonPackageName.event.Event
import $commonPackageName.event.EventBus
import $commonPackageName.runOnIoThread
import $webServicePackageName.dto.ErrorDTO
import $webServicePackageName.dto.Resource

abstract class BaseRepository {

    abstract val eventBus: EventBus

    protected suspend fun networkRun(
        block: suspend () -> Resource<out Any>,
        onSuccess: (suspend (Any) -> Unit)? = null
    ): Boolean {
        val result = runOnIoThread(block)

        if (result.success)
            onSuccess?.invoke(result.data)
        // else
        //    eventBus.produceEvent(Event.ToastEvent((result.data as ErrorDTO).message))

        return result.success
    }

}
"""