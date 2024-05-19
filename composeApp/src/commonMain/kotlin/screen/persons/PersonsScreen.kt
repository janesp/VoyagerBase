package screen.persons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import data.persons.Person

class PersonsScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel: PersonsScreenModel = koinScreenModel()
        val persons = screenModel.persons.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Persons") },
                )
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                println("PersonsScreen - content persons: ${screenModel.persons.value}")
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 200.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    items(persons.value) {person: Person ->
                        PersonRow(person)
                    }
                }
            }
        }
    }

    @Composable
    fun PersonRow(person: Person) {
//        Text("${person.firstName} ${person.lastName} - ${person.dateOfBirth}")
        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(color = Color.LightGray)
        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text("${person.firstName} ${person.lastName}")
                Text("BOD - ${person.dateOfBirth}")
            }
        }
    }

}