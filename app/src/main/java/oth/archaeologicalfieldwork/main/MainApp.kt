package oth.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.SplashScreenHelper
import oth.archaeologicalfieldwork.models.SiteMemStore
import oth.archaeologicalfieldwork.models.SiteModel

class MainApp : Application(), AnkoLogger {

    //lateinit var sites: SiteStore

    val sites = SiteMemStore()

    override fun onCreate() {
        super.onCreate()

        info("Main App started...")

        //val sites = SiteMemStore()
        //sites = SiteJSONStore(applicationContext)
        //sites = FieldStoreRoom(applicationContext)
        //sites = PlacemarkFireStore(applicationContext)

        sites.create(
            SiteModel(
                1,
                "Site 01 Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01Site 01",
                "this is site 01 thisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthisthis this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this this  this this this this this this this  is site 01 this is site 01this is site 01 this is site 01this is site 01this is site 01this is site 01v"
            )
        )
        sites.create(SiteModel(2, "Site 02", "this is site 02"))
        sites.create(SiteModel(3, "Site 03", "this is site 03"))
        sites.create(SiteModel(4, "Site 04", "this is site 04"))

        // register the util to remove splash screen after loading
        registerActivityLifecycleCallbacks(SplashScreenHelper())
    }
}