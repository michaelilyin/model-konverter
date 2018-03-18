package konverter.benchmark

import konverter.Gender
import konverter.Konverter
import konverter.KonverterFactory
import konverter.PersonContainer
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.openjdk.jmh.runner.Runner
import java.time.LocalDate
import org.openjdk.jmh.runner.options.OptionsBuilder

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput, Mode.AverageTime, Mode.SingleShotTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, batchSize = 10)
@Fork(2)
open class SimpleBench {

    val converter = KonverterFactory create {
        map from PersonContainer::class to PersonContainer::class
    }
    val predefined = PersonContainer(
            "Harry",
            "James",
            "Potter",
            10,
            LocalDate.now(),
            Gender.MALE
    )

    @Benchmark
    fun convert(blackhole: Blackhole) {
        blackhole.consume(converter.convert(predefined, PersonContainer::class))
    }

    @Benchmark
    fun convertManual(blackhole: Blackhole) {
        blackhole.consume(PersonContainer(
                predefined.firstName,
                predefined.secondName,
                predefined.lastName,
                predefined.age,
                predefined.birthday,
                predefined.gender
        ))
    }

}