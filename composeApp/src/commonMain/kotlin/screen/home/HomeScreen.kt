package screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import screen.details.DetailsScreen
import guiplayground.composeapp.generated.resources.Res
import guiplayground.composeapp.generated.resources.app_name

class HomeScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val appName = stringResource(Res.string.app_name)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box() {
                    Text(text = appName.toString())
                }
                Box(
                ) {
                    Button(onClick = { navigator?.push(DetailsScreen(number = 10))}) {
                        Text("GO")
                    }
                }
            }
        }
    }
}