package com.andyho.sampleapplication

import androidx.multidex.MultiDexApplication
import com.andyho.sampleapplication.injection.component.AppComponent
import com.andyho.sampleapplication.injection.component.DaggerAppComponent
import com.andyho.sampleapplication.manager.SessionManager
import com.andyho.sampleapplication.manager.SharePreferenceManager

class SampleApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initManagers()
    }

    private fun initManagers() {
        SessionManager.context = this
        SharePreferenceManager.context = this
    }

    companion object {
        lateinit var INSTANCE: SampleApplication
    }
}