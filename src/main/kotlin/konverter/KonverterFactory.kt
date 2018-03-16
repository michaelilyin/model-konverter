package konverter

import konverter.builder.KonverterBuilder
import konverter.builder.impl.KonverterBuilderImpl
import mu.KLogging

object KonverterFactory: KLogging() {

    fun create(): Konverter {
        return this create {

        }
    }

    infix fun create(build: KonverterBuilder.() -> Unit): Konverter {
        val builder = KonverterBuilderImpl()
        build(builder)
        return builder.build()
    }

}