package konverter.builder.impl

import konverter.KonverterFactory
import konverter.builder.DestinationBuilder
import konverter.builder.KonverterBuilder
import konverter.builder.MappingBuilder
import konverter.builder.impl.data.DestinationData
import konverter.builder.impl.data.SourceData
import kotlin.reflect.KClass

class DestinationBuilderImpl<S: Any>(
        private val konverterBuidler: KonverterBuilderImpl,
        private val source: SourceData<S>
): DestinationBuilder<S> {

    override fun <D : Any> to(target: KClass<D>): MappingBuilder<S, D> {
        val mapping = MappingBuilderImpl(source, DestinationData(target))
        konverterBuidler.registerMapping(mapping)
        return mapping
    }

}