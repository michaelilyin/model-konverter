package konverter.processor

import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty

abstract class Processor<S: Any, D: Any> {

    private val queue = ArrayDeque<MutableMap<KParameter, Any?>>()

    abstract fun process(from: S): D

    protected fun borrowMap(): MutableMap<KParameter, Any?> {
        synchronized(queue) {
            return if (queue.size > 0) {
                queue.pop()
            } else {
                mutableMapOf()
            }
        }
    }

    protected fun returnMap(map: MutableMap<KParameter, Any?>) {
        synchronized(queue) {
            map.clear()
            queue.add(map)
        }
    }
}