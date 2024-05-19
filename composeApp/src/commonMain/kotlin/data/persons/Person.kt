package data.persons

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val dateOfBirth: String,
    val firstName: String,
    val lastName: String
)
