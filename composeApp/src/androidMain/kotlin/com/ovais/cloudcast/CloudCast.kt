package com.ovais.cloudcast

import android.app.Application
import com.ovais.cloudcast.di.initKoin
import org.koin.android.ext.koin.androidContext

class CloudCast : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CloudCast)
        }
    }
}