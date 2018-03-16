package konverter.strategy

import konverter.processor.Processor
import kotlin.reflect.KClass

class PropertyBasedStrategy : AlgorithmStrategy {

    override fun <S : Any, D : Any> createProcessor(from: KClass<S>, to: KClass<D>): Processor<S, D> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}