package konverter

import konverter.builder.impl.data.MappingDefinition
import konverter.processor.Processor
import konverter.strategy.AutoDetectionStrategy
import mu.KLogging
import kotlin.reflect.KClass

class Konverter internal constructor(
        definitions: List<MappingDefinition<*, *>>
) {

    companion object: KLogging()

    private val processors: MutableMap<KClass<*>, MutableMap<KClass<*>, Processor<*, *>>> = mutableMapOf()
    private val dynamicDetectionStrategy = AutoDetectionStrategy()

    init {
        definitions.forEach {
            val targets = processors.computeIfAbsent(it.source, { mutableMapOf() })
            targets[it.target] = it.processor
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <S : Any, T : Any> convert(source: S, target: KClass<T>): T {
        val targets = processors.computeIfAbsent(source::class, { mutableMapOf() })
        var processor = targets[target] as Processor<in S, T>?
        if (processor == null) {
            processor = generateProcessor(source::class, target) as Processor<S, T>
            targets[target] = processor
        }
        return processor.process(source)
    }

    private fun <S: Any, T: Any> generateProcessor(from: KClass<S>, to: KClass<T>): Processor<S, T> {
        logger.debug { "Construct conversion processor dynamically for source = ${from.simpleName} to ${to.simpleName}" }
        return dynamicDetectionStrategy.createProcessor(from, to)
    }
}