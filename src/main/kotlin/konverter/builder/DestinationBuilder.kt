package konverter.builder

import kotlin.reflect.KClass

interface DestinationBuilder<S : Any> {
    infix fun <D : Any> to(target: KClass<D>): MappingBuilder<S, D>
}