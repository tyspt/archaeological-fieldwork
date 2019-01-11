package oth.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.SplashScreenHelper
import oth.archaeologicalfieldwork.models.UserSession
import oth.archaeologicalfieldwork.models.sites.SiteJSONStore
import oth.archaeologicalfieldwork.models.sites.SiteModel
import oth.archaeologicalfieldwork.models.sites.SiteStore
import oth.archaeologicalfieldwork.models.users.UserJSONStore
import oth.archaeologicalfieldwork.models.users.UserStore

class MainApp : Application(), AnkoLogger {

    lateinit var sites: SiteStore
    lateinit var users: UserStore
    lateinit var session: UserSession

    override fun onCreate() {
        super.onCreate()

        session = UserSession(applicationContext)

        info("Main App started...")

        //sites = SiteMemStore()
        sites = SiteJSONStore(applicationContext)
        //sites = FieldStoreRoom(applicationContext)
        //sites = PlacemarkFireStore(applicationContext)

        users = UserJSONStore(applicationContext)

        if (sites.findAll().isEmpty()) {
            sites.create(
                SiteModel(
                    1,
                    "Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01 Site 01",
                    "this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 this is site 01 "
                )
            )
            sites.create(SiteModel(2, "Site 02", "this is site 02"))
            sites.create(SiteModel(3, "Site 03", "this is site 03"))
            sites.create(SiteModel(4, "Site 04", "this is site 04"))
        }

        // register the util to remove splash screen after loading
        registerActivityLifecycleCallbacks(SplashScreenHelper())
    }
}