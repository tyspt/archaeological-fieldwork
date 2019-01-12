package oth.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.SplashScreenHelper
import oth.archaeologicalfieldwork.models.Session
import oth.archaeologicalfieldwork.models.sites.SiteStore
import oth.archaeologicalfieldwork.models.users.UserJSONStore
import oth.archaeologicalfieldwork.models.users.UserStore

class MainApp : Application(), AnkoLogger {

    lateinit var session: Session
    lateinit var users: UserStore
    var sites: SiteStore? = null        //will be initialised by user login

    override fun onCreate() {
        super.onCreate()

        session = Session(applicationContext)
        users = UserJSONStore(applicationContext)

        info("Main App started...")

        // register the util to remove splash screen after loading
        registerActivityLifecycleCallbacks(SplashScreenHelper())
    }
}