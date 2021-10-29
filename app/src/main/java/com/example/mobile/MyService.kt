package com.example.mobile

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        onTaskRemoved(intent)
        Toast.makeText(applicationContext,"service running in background",Toast.LENGTH_SHORT).show()

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartServiceIntent = Intent(applicationContext,this.javaClass)//create restartServiceIntent method
        restartServiceIntent.setPackage(packageName)//set the value on the intent
        startService(restartServiceIntent)//start the intent on stratService method
        super.onTaskRemoved(rootIntent)
    }



}