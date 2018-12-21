package oth.archaeologicalfieldwork.views.site

import android.content.Intent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.editsite.EditSiteView

class SitePresenter(val siteView: SiteView) : AnkoLogger {

    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2

    var site = SiteModel()
    var app: MainApp = siteView.application as MainApp

    init {
        if (siteView.intent.hasExtra("site_show")) {
            site = siteView.intent.extras.getParcelable<SiteModel>("site_show")
            siteView.showSite(site)
            info("site-show opened")
        }
    }

    fun doEditSite(site: SiteModel) {
        siteView.startActivityForResult(siteView.intentFor<EditSiteView>().putExtra("site_edit", site), 0)
    }

    fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE_REQUEST -> {
                site.images.add(data.data.toString())
                siteView.showSite(site)
            }
            /* LOCATION_REQUEST -> {
                 location = data.extras.getParcelable<Location>("location")
                 placemark.lat = location.lat
                 placemark.lng = location.lng
                 placemark.zoom = location.zoom
             }*/
        }
    }

}