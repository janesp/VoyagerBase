package screen.prescriptions

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
import data.prescriptions.Prescription

class PrescriptionsScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel: PrescriptionsScreenModel = koinScreenModel()
        val prescriptions = screenModel.prescriptions.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Prescriptions") },
                )
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                println("PrescriptionsScreen - content prescriptions: ${screenModel.prescriptions.value}")
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 200.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    items(prescriptions.value) {prescription: Prescription ->
                        PrescriptionRow(prescription)
                    }
                }
            }
        }
    }

    @Composable
    fun PrescriptionRow(prescription: Prescription) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(color = Color.LightGray)
        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text("${prescription.name} (${prescription.medicationId})")
                Text("${prescription.dose} / ${prescription.frequency}")
                Text("Duration - ${prescription.duration}")
            }
        }
    }

}