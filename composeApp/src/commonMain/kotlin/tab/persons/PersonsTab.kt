package tab.persons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import screen.persons.PersonsScreen

object PersonsTab : Tab {
    @Composable
    override fun Content() {
        Navigator(PersonsScreen()) { navigator ->
            SlideTransition((navigator))
        }
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 4u,
                title = "Persons",
                icon = null
            )
        }

}