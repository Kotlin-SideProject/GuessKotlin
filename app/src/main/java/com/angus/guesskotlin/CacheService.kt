package com.angus.guesskotlin

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CacheService() : IntentService("CacheService") {
    private val TAG: String? = CacheService::class.java.simpleName

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind:");
        return null
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent:");
        Thread.sleep(5000)

    }

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.d(TAG, "onStartCommand:");
//        return START_STICKY
//    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate:");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy:");
    }
}