package konverter.builder.impl

import konverter.builder.DestinationBuilder
import konverter.builder.KonverterBuilder
import konverter.builder.SourceBuilder
import konverter.builder.impl.data.SourceData
import kotlin.reflect.KClass

class SourceBuilderImpl(
        private val konverterBuidler: KonverterBuilderImpl
) : SourceBuilder {

    override fun <T : Any> from(source: KClass<T>): DestinationBuilder<T> {
        return DestinationBuilderImpl(konverterBuidler, SourceData(source))
    }

}