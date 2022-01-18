package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.broadcastreceiver.databinding.ActivityLottoBinding

class Lotto : AppCompatActivity() {
    val lottoBroadcastReceiver: BroadcastReceiver = LottoBroadcastReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)

        // init the textview for lotto nummbers

        val intentFilter = IntentFilter("eric.tamk.lotto")
        registerReceiver(lottoBroadcastReceiver, intentFilter)
    }

    fun startLotto(view: android.view.View) {
        val intent = Intent(this, LottoService::class.java)
        Toast.makeText(this, "Running lottery service", Toast.LENGTH_SHORT).show()
        intent.putExtra("AMOUNT_OF_NUMBERS", 7)
        startService( intent )
    }

    inner class LottoBroadcastReceiver : BroadcastReceiver()  {
        override fun onReceive(p0: Context?, intent: Intent?) {
            val lottoNumbers = intent?.getIntArrayExtra("LOTTO_NUMBER")
            var lottoString: String = ""
            lottoNumbers?.forEach { lottoString += "$it " }

            findViewById<TextView>(R.id.lotto).text = lottoString
        }

    }


}