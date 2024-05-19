import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import data.NetworkApi
import data.persons.PersonRepository
import data.prescriptions.PrescriptionsRepository
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.module
import screen.details.DetailsViewModel
import screen.persons.PersonsScreenModel
import screen.prescriptions.PrescriptionsScreenModel
import tab.home.HomeTab
import tab.persons.PersonsTab
import tab.prescriptions.PrescriptionsTab
import tab.profile.ProfileTab
import tab.settings.SettingsTab

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    initKoin()
    MaterialTheme {
        TabNavigator(HomeTab) {
            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(ProfileTab)
                        TabNavigationItem(PersonsTab)
                        TabNavigationItem(PrescriptionsTab)
                        TabNavigationItem(SettingsTab)
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text((tab.options.title)) },
        icon = {}
    )
}

val networkModule = module {
    factory { DetailsViewModel(NetworkApi()) }
}

val personsModule = module {
    factory { PersonsScreenModel(PersonRepository()) }
}

val prescriptionsModule = module {
    factory { PrescriptionsScreenModel(PrescriptionsRepository()) }
}

fun initKoin() {
    startKoin {
        modules(listOf(networkModule, personsModule, prescriptionsModule))
    }
}