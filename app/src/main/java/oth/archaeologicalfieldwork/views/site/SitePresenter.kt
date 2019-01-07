package oth.archaeologicalfieldwork.views.site

import android.content.Intent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.editsite.AddOrEditSiteView

class SitePresenter(val view: SiteView) : AnkoLogger {
    val SITE_EDIT = 1

    var site = SiteModel()
    var app: MainApp = view.application as MainApp

    init {
        if (view.intent.hasExtra("site_show")) {
            site = view.intent.extras.getParcelable("site_show")
            view.showSite(site)
            info("site-show opened")
        }
    }

    fun doEditSite(site: SiteModel) {
        view.startActivityForResult(view.intentFor<AddOrEditSiteView>().putExtra("site_edit", site), SITE_EDIT)
    }

    fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            SITE_EDIT -> {
                site = data.extras.getParcelable("changed_site")
                view.showSite(site)
            }

            //TODO edit site visited status / visit date
        }
    }

}