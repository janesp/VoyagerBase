package screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.NetworkApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val api: NetworkApi
): ScreenModel {
    private var _number = mutableStateOf(0)
    val number: State<Int> = _number

    init {
        println("DetailsViewModel: Initializing...")
        screenModelScope.launch {
            delay(2000)
            println("Fetched data: ${api.fetchData()}")
            _number.value = 10
        }
    }

    override fun onDispose() {
        super.onDispose()
        println("DetailsViewModel: Disposing...")
    }
}