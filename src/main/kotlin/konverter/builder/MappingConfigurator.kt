package konverter.builder

interface MappingConfigurator<S : Any, D: Any> {
    val with: MappingExtensionBuilder<S, D>
}