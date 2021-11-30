package com.example.broadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlin.random.Random

class LottoService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // example of getting extra from intent:
        //val amountOfNumbers = intent?.getIntExtra("AMOUNT_OF_NUMBERS", 0)

        Thread {
            Thread.sleep(1500)
            val lottoNumber = IntArray(7) { Random.nextInt(0,40) }
            val lottoBroadcast = Intent("eric.tamk.lotto")
            // add lotto number data to the broadcast
            lottoBroadcast.putExtra("LOTTO_NUMBER", lottoNumber)
            // send broadcast
            sendBroadcast( lottoBroadcast )
        }.start() //starts a new thread in the system

        return START_STICKY
    }
}