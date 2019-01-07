package oth.archaeologicalfieldwork.views.editsite

import android.content.Intent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.showImagePicker
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel

class EditSitePresenter(val editSiteView: EditSiteView) : AnkoLogger {

    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2

    var site = SiteModel()
    var app: MainApp = editSiteView.application as MainApp

    var edit = false

    init {
        if (editSiteView.intent.hasExtra("site_edit")) {
            site = editSiteView.intent.extras.getParcelable<SiteModel>("site_edit")
            editSiteView.fetchSiteInformation(site)
            info("site-edit opened")
        }
    }

    fun doAddOrSaveSite(site: SiteModel) {
        if (site != null || site.title.toString().isEmpty()) {
            info(R.string.enter_site_title)
        } else {
            if (edit) {
                app.sites.update(site)
            } else {
                app.sites.create(site)
            }
        }
        editSiteView.finish()
    }

    fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE_REQUEST -> {
                site.images.add(data.data.toString())
                editSiteView.fetchSiteInformation(site)
            }
            /* LOCATION_REQUEST -> {
                 location = data.extras.getParcelable<Location>("location")
                 placemark.lat = location.lat
                 placemark.lng = location.lng
                 placemark.zoom = location.zoom
             }*/
        }
    }

    fun doSelectImage() {
        showImagePicker(editSiteView, IMAGE_REQUEST)
    }
}