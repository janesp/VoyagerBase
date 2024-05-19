package screen.prescriptions

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.prescriptions.Prescription
import data.prescriptions.PrescriptionsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PrescriptionsScreenModel(
    private val prescriptionsRepository: PrescriptionsRepository
): ScreenModel {
    private val _prescriptions = MutableStateFlow(prescriptionsRepository.getPrescriptions())
    val prescriptions: StateFlow<List<Prescription>> = _prescriptions.asStateFlow()

    init {
        println("PrescriptionsScreenModel: Initializing...")
        screenModelScope.launch {
            println("PrescriptionsScreenModel init - _prescriptions before value: ${_prescriptions.value}")
            delay(2000)
            loadPrescriptions()
            println("PrescriptionsScreenModel init - _prescriptions after value: ${_prescriptions.value}")
            println("PrescriptionsScreenModel init - prescriptions value: ${prescriptions.value}")
        }
    }

    suspend fun loadPrescriptions() {
        val newPrescriptions = (prescriptionsRepository.fetchPrescriptions())
        prescriptionsRepository.addPrescriptions(newPrescriptions)
        _prescriptions.value = prescriptionsRepository.getPrescriptions()
    }

    override fun onDispose() {
        super.onDispose()
        println("PrescriptionsScreenModel: Disposing...")
    }

}