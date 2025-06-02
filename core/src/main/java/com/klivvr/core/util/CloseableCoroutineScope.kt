package com.klivvr.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.io.Closeable
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CloseableCoroutineScope @Inject constructor(
    private val context: CoroutineContext
) : Closeable, CoroutineScope {

    override val coroutineContext: CoroutineContext = context

    override fun close() {
        coroutineContext.cancel()
    }
}
