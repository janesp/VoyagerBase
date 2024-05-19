package data.prescriptions

import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import guiplayground.composeapp.generated.resources.Res

class PrescriptionsRepository {
    private val _prescriptions = mutableListOf<Prescription>()

    @OptIn(ExperimentalResourceApi::class)
    suspend fun fetchPrescriptions(): List<Prescription> {
        val bytes = Res.readBytes("files/prescription_items.json")
        val prescriptions = Json.decodeFromString<List<Prescription>>(bytes.decodeToString())
        return prescriptions
    }

    fun getPrescriptions(): List<Prescription> = _prescriptions

    fun addPrescriptions(prescriptions: List<Prescription>) {
        _prescriptions.addAll(prescriptions)
    }
}
