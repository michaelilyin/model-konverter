package konverter

import konverter.builder.impl.data.MappingDefinition
import konverter.processor.Processor
import konverter.strategy.AutoDetectionStrategy
import mu.KLogging
import kotlin.reflect.KClass
import java.util.function.Function as JFunction

class Konverter internal constructor(
        definitions: List<MappingDefinition<*, *>>
) {

    companion object: KLogging()

    private val processors: MutableMap<KClass<*>, MutableMap<KClass<*>, Processor<*, *>>> = mutableMapOf()
    private val dynamicDetectionStrategy = AutoDetectionStrategy()

    private val mapFactory: JFunction<KClass<*>, MutableMap<KClass<*>, Processor<*,*>>> = JFunction { mutableMapOf() }

    init {
        definitions.forEach {
            val targets = processors.computeIfAbsent(it.source, mapFactory)
            targets[it.target] = it.processor
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <S : Any, T : Any> convert(source: S, target: KClass<T>): T {
        val targets = processors[source::class]
        var processor = targets?.get(target) as Processor<in S, T>?
        if (processor == null) {
            val nonnullTargets = processors.computeIfAbsent(source::class, mapFactory)
            processor = generateProcessor(source::class, target) as Processor<S, T>
            nonnullTargets[target] = processor
        }
        return processor.process(source)
    }

    private fun <S: Any, T: Any> generateProcessor(from: KClass<S>, to: KClass<T>): Processor<S, T> {
        logger.debug { "Construct conversion processor dynamically for source = ${from.simpleName} to ${to.simpleName}" }
        return dynamicDetectionStrategy.createProcessor(from, to)
    }
}