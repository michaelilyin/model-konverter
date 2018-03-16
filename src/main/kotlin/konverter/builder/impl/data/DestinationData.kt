package konverter.builder.impl.data

import kotlin.reflect.KClass

data class DestinationData<D : Any>(val type: KClass<D>)