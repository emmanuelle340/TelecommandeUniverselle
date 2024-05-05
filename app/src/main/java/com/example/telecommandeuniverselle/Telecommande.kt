package com.example.telecommandeuniverselle

import android.hardware.ConsumerIrManager
import android.content.Context

class Telecommande(context: Context) {

    private var irManager: ConsumerIrManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    // Vérifie si le périphérique dispose d'un émetteur infrarouge
    fun hasIrEmitter(): Boolean {
        return irManager.hasIrEmitter()
    }

    // Envoie un signal infrarouge
    fun sendIrSignal(frequency: Int, pattern: IntArray) {
        if (hasIrEmitter()) {
            irManager.transmit(frequency, pattern)
            //println("Signal transmis")
        }
    }


}


