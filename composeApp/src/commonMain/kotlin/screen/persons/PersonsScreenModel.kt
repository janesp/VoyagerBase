package screen.persons

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.persons.Person
import data.persons.PersonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PersonsScreenModel(private val personRepository: PersonRepository): ScreenModel {
    private val _persons = MutableStateFlow(personRepository.getPersons())
    val persons: StateFlow<List<Person>> = _persons.asStateFlow()

    init {
        println("PersonsScreenModel: Initializing...")
        screenModelScope.launch {
            println("PersonsScreenModel init - _persons before value: ${_persons.value}")
            delay(2000)
            loadPersons()
            println("PersonsScreenModel init - _persons after value: ${_persons.value}")
            println("PersonsScreenModel init - persons value: ${persons.value}")
        }
    }

    suspend fun loadPersons() {
        val newPersons = (personRepository.fetchPersons())
        personRepository.addPersons(newPersons)
        _persons.value = personRepository.getPersons()
    }

    override fun onDispose() {
        super.onDispose()
        println("PersonsScreenModel: Disposing...")
    }

}