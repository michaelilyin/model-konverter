package konverter.strategy

import konverter.processor.Processor
import kotlin.reflect.KClass

class AutoDetectionStrategy : AlgorithmStrategy {

    override fun <S : Any, D : Any> createProcessor(from: KClass<S>, to: KClass<D>): Processor<S, D> {
        val internalStrategy = if (to.isData) {
            ConstructorBasedStrategy()
        } else if (to.constructors.any { it.parameters.isEmpty() }) {
            PropertyBasedStrategy()
        } else {
            throw IllegalArgumentException("Only data classes and classes with default constructor are supported")
        }
        return internalStrategy.createProcessor(from, to)
    }

}