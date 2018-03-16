package konverter.processor

interface Processor<S: Any, D: Any> {
    fun process(from: S): D
}