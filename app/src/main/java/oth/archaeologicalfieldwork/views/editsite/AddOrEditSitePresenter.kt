package oth.archaeologicalfieldwork.views.addoreditsite

import android.app.Activity
import android.content.Intent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.showImagePicker
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.editsite.AddOrEditSiteView

class AddOrEditSitePresenter(val view: AddOrEditSiteView) : AnkoLogger {

    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2

    var site = SiteModel()
    var app: MainApp = view.application as MainApp

    var edit = false

    init {
        if (view.intent.hasExtra("site_edit")) {
            site = view.intent.extras.getParcelable("site_edit")
            view.showSiteInformation(site)
            edit = true
            info("site-edit opened")
        }
    }

    fun doAddOrSaveSite(site: SiteModel) {
        if (site.title.isEmpty()) {
            info(R.string.enter_site_title)
        } else {
            if (edit) {
                app.sites.update(site)
                val resultIntent = Intent()
                resultIntent.putExtra("changed_site", site)
                view.setResult(Activity.RESULT_OK, resultIntent)
            } else {
                app.sites.create(site)
            }
        }
        view.finish()
    }

    fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE_REQUEST -> {
                view.site = this.site
                site.images.add(data.data.toString())
                view.displaySiteImages(site)
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
        showImagePicker(view, IMAGE_REQUEST)
    }

    fun doCancel() {
        view.finish()
    }

}