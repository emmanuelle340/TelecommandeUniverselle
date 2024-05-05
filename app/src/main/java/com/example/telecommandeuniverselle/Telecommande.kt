package com.example.telecommandeuniverselle

import android.hardware.ConsumerIrManager
import android.content.Context
import androidx.compose.material3.Text

class Telecommande(context: Context) {

    private var irManager: ConsumerIrManager? = null

    init {
        try {
            irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager
        } catch (e: Exception) {
            // Gérer l'exception ici
            e.printStackTrace()

        }
    }

    // Vérifie si le périphérique dispose d'un émetteur infrarouge
    fun hasIrEmitter(): Boolean? {
        return irManager?.hasIrEmitter()
    }

    // Envoie un signal infrarouge
    fun sendIrSignal(frequency: Int, pattern: IntArray) {
        if (hasIrEmitter() == true) {
            irManager?.transmit(frequency, pattern)
            //println("Signal transmis")
        }
    }


}


