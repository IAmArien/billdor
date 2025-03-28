package com.idea.billdor.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BillDorApplication : Application() {

    companion object {
        private lateinit var context: Application
        private fun installContext(application: Application) {
            this.context = application
        }
        fun getContext() = context
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        installContext(this)
    }
}
