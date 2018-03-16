package konverter

import java.time.LocalDate

data class StringContainer(val value: String)
data class IntContainer(val value: Int)
data class FloatContainer(val value: Float)

enum class Gender{
    MALE,FEMALE
}

data class PersonContainer(
        val firstName: String,
        val secondName: String,
        val lastName: String,
        val age: Number,
        val birthday: LocalDate,
        val gender: Gender
)