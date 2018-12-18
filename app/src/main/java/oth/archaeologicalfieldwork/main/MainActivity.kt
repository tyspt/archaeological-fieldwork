package oth.archaeologicalfieldwork.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R

class MainActivity : AppCompatActivity(), AnkoLogger {

    //lateinit var sites: SitesStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sites = SitesJSONStore(applicationContext)
        //sites = FieldStoreRoom(applicationContext)
        //sites = PlacemarkFireStore(applicationContext)

        info("Main Activity started..")
    }
}
