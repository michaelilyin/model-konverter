package konverter.builder

import kotlin.reflect.KClass

interface SourceBuilder {
    infix fun <T : Any> from(source: KClass<T>): DestinationBuilder<T>
}