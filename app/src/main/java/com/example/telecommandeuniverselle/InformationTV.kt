package com.example.telecommandeuniverselle

class InformationTV(
    val protocol: String? = null,
    val address: String? = null,
    val command: List<String?> = emptyList(),
    val frequency: String? = null,
    val duty_cycle: String? = null,
    val data: List<String?> = emptyList()
)