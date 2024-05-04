package com.example.telecommandeuniverselle

class InformationTV(
    val type: String? = null,
    val protocol: String? = null,
    val address: String? = null,
    val command: List<String?> = emptyList(),
    val frequency: String? = null,
    val dutyCycle: String? = null,
    val data: List<String?> = emptyList()
){
    companion object {
        fun afficherListe(informations: List<InformationTV>) {
            for (info in informations) {
                println("Type: ${info.type}")
                println("Protocol: ${info.protocol}")
                println("Address: ${info.address}")
                println("Command: ${info.command}")
                println("Frequency: ${info.frequency}")
                println("Duty Cycle: ${info.dutyCycle}")
                println("Data: ${info.data}")
                println()
            }
        }
    }
}