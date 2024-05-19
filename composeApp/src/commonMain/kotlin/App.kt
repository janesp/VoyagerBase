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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.module
import screen.details.DetailsViewModel
import tab.home.HomeTab
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

val myModule = module {
    factory { DetailsViewModel(NetworkApi()) }
}

fun initKoin() {
    startKoin {
        modules(myModule)
    }
}