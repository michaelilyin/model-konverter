package konverter.builder.impl

import konverter.builder.MappingBuilder
import konverter.builder.MappingConfigurator
import konverter.builder.impl.data.DestinationData
import konverter.builder.impl.data.MappingDefinition
import konverter.builder.impl.data.SourceData
import mu.KLogging

class MappingBuilderImpl<S: Any, D: Any>(
        private val source: SourceData<S>,
        private val target: DestinationData<D>
): MappingBuilder<S, D> {

    companion object: KLogging()

    private val configurator = MappingConfiguratorImpl<S, D>()

    override fun configured(build: MappingConfigurator<S, D>.() -> Unit) {
        build(configurator)
    }

    internal fun build(): MappingDefinition<S, D> {
        logger.debug {
            "Build mapping definition with source=$source, target=$target " +
                    "using strategy=${configurator.strategy::class.simpleName}"
        }
        val processor = configurator.strategy.createProcessor(source.type, target.type)
        return MappingDefinition(
                source.type,
                target.type,
                processor
        )
    }
}