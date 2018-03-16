package konverter.benchmark

import konverter.Gender
import konverter.Konverter
import konverter.KonverterFactory
import konverter.PersonContainer
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.runner.Runner
import java.time.LocalDate
import org.openjdk.jmh.runner.options.OptionsBuilder




class SimpleBench {

    lateinit var converter: Konverter
    val predefined = PersonContainer(
            "Harry",
            "James",
            "Potter",
            10,
            LocalDate.now(),
            Gender.MALE
    )

    fun setUp() {
        converter = KonverterFactory create {
            map from PersonContainer::class to PersonContainer::class
        }
    }

    init {
        setUp()
    }

    @Benchmark
    fun predefined(reps: Int) {
        for (i in 0 until reps) {
            converter.convert(predefined, PersonContainer::class)
        }
    }

    @Benchmark
    fun predefinedManual(reps: Int) {
        for (i in 0 until reps) {
            PersonContainer(
                    predefined.firstName,
                    predefined.secondName,
                    predefined.lastName,
                    predefined.age,
                    predefined.birthday,
                    predefined.gender
            )
        }
    }

    @Benchmark
    fun creation(reps: Int) {
        val created = PersonContainer(
                "Harry",
                "James",
                "Potter",
                10,
                LocalDate.now(),
                Gender.MALE
        )
        for (i in 0 until reps) {
            converter.convert(created, PersonContainer::class)
        }
    }

    @Benchmark
    fun creationManual(reps: Int) {
        val created = PersonContainer(
                "Harry",
                "James",
                "Potter",
                10,
                LocalDate.now(),
                Gender.MALE
        )
        for (i in 0 until reps) {
            PersonContainer(
                    created.firstName,
                    created.secondName,
                    created.lastName,
                    created.age,
                    created.birthday,
                    created.gender
            )
        }
    }

}

fun main(args: Array<String>) {
    val opt = OptionsBuilder()
            .include("konverter.benchmark.*")
            .warmupIterations(5)
            .measurementIterations(5)
            .forks(1)
            .build()
    Runner(opt).run()
}