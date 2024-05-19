package tab.prescriptions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import screen.persons.PersonsScreen
import screen.prescriptions.PrescriptionsScreen

object PrescriptionsTab : Tab {
    @Composable
    override fun Content() {
        Navigator(PrescriptionsScreen()) { navigator ->
            SlideTransition((navigator))
        }
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 5u,
                title = "Prescriptions",
                icon = null
            )
        }

}