package data.persons

import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import guiplayground.composeapp.generated.resources.Res

class PersonRepository {
    private val _persons = mutableListOf<Person>()

    @OptIn(ExperimentalResourceApi::class)
    suspend fun fetchPersons(): List<Person> {
        val bytes = Res.readBytes("files/persons.json")
        val persons = Json.decodeFromString<List<Person>>(bytes.decodeToString())
        return persons
    }

    fun getPersons(): List<Person> = _persons

    fun addPersons(persons: List<Person>) {
        _persons.addAll(persons)
    }
}
