package konverter.builder

interface MappingBuilder<S : Any, D: Any> {
    infix fun configured(build: MappingConfigurator<S, D>.() -> Unit)
}