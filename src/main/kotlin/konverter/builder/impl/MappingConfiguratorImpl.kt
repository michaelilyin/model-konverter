package konverter.builder.impl

import konverter.builder.MappingConfigurator
import konverter.builder.MappingExtensionBuilder
import konverter.strategy.AlgorithmStrategy
import konverter.strategy.AutoDetectionStrategy

class MappingConfiguratorImpl<S: Any, D: Any>: MappingConfigurator<S, D>, MappingExtensionBuilder<S, D> {

    override val with: MappingExtensionBuilder<S, D>
        get() = this

    internal var strategy: AlgorithmStrategy = AutoDetectionStrategy()

    override fun strategy(strategy: AlgorithmStrategy) {
        this.strategy = strategy
    }
}