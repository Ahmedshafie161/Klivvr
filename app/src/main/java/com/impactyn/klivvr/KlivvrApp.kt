package com.impactyn.klivvr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KlivvrApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}