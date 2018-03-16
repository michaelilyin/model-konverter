package konverter.builder.impl.data

import kotlin.reflect.KClass

data class SourceData<S : Any>(val type: KClass<S>)