package oth.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.SplashScreenHelper

class MainApp : Application(), AnkoLogger {

    override fun onCreate() {
        super.onCreate()

        info("Main App started")

        // register the util to remove splash screen after loading
        registerActivityLifecycleCallbacks(SplashScreenHelper())
    }
}