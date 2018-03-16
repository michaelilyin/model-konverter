package konverter.builder.impl.data

import konverter.processor.Processor
import kotlin.reflect.KClass

data class MappingDefinition<S: Any, D: Any>(
        val source: KClass<S>,
        val target: KClass<D>,
        val processor: Processor<S, D>
)