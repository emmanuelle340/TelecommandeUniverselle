package com.example.telecommandeuniverselle

class InformationTV(
    val frequency: Int ,

    val data: List<Int> = emptyList()
){
    fun display() {
        println("Frequency: $frequency")
        println("Data:")
        data.forEachIndexed { index, item ->
            println("[$index]: $item")
        }
    }
}