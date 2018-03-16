package konverter.strategy

import konverter.processor.Processor
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

class ConstructorBasedStrategy : AlgorithmStrategy {

    override fun <S : Any, D : Any> createProcessor(from: KClass<S>, to: KClass<D>): Processor<S, D> {
        val constructor = to.primaryConstructor
                ?: throw IllegalArgumentException(
                        "Primary constructor should be defined for constructor based strategy"
                )
        val properties = from.memberProperties
        val params = constructor.parameters

        val conversionMap = params.map { param ->
            val property = properties.find { prop ->
                prop.name == param.name
            } ?: throw IllegalStateException(
                    "Can not find property for initialize constructor parameter ${param.name} for type ${to.simpleName}"
            )
            param to property
        }.toMap()

        return object : Processor<S, D> {
            override fun process(from: S): D {
                val args = conversionMap.asSequence()
                        .map {
                            val param = it.key
                            val value = it.value.get(from)
                            param to value
                        }.toMap()
                return constructor.callBy(args)
            }
        }
    }
}