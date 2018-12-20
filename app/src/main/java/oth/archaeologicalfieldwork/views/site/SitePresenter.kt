package oth.archaeologicalfieldwork.views.site

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel

class SitePresenter(val siteView: SiteView) : AnkoLogger {

    var site = SiteModel()
    var app: MainApp

    init {
        app = siteView.application as MainApp

        if (siteView.intent.hasExtra("site_show")) {
            site = siteView.intent.extras.getParcelable<SiteModel>("site_show")
            siteView.showSite(site)
            info("site-show opened")
        }
    }


}