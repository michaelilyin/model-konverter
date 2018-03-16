package konverter.builder

import konverter.strategy.AlgorithmStrategy

interface MappingExtensionBuilder<S : Any, D: Any> {
    infix fun strategy(strategy: AlgorithmStrategy)
}