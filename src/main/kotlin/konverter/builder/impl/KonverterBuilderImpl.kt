package konverter.builder.impl

import konverter.Konverter
import konverter.builder.KonverterBuilder
import konverter.builder.SourceBuilder

class KonverterBuilderImpl : KonverterBuilder {

    private val mappings = mutableListOf<MappingBuilderImpl<*, *>>()

    internal fun build(): Konverter {
        val definitions = mappings.map { it.build() }
        return Konverter(definitions)
    }

    internal fun registerMapping(mapping: MappingBuilderImpl<*, *>) {
        this.mappings.add(mapping)
    }

    override val map: SourceBuilder
        get() = SourceBuilderImpl(this)
}