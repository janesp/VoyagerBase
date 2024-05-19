package data.prescriptions

import kotlinx.serialization.Serializable

@Serializable
data class Prescription(
    val dose: String,
    val duration: String,
    val frequency: String,
    val medicationId: String,
    val name: String
)