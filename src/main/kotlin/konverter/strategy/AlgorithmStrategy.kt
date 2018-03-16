package konverter.strategy

import konverter.processor.Processor
import kotlin.reflect.KClass

interface AlgorithmStrategy {
    fun <S: Any, D: Any> createProcessor(from: KClass<S>, to: KClass<D>): Processor<S, D>
}