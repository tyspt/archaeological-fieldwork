package oth.archaeologicalfieldwork.views.site

import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel

class SitePresenter(val view: SiteView) {

    var site = SiteModel()

    var app: MainApp

    init {
        app = view.application as MainApp

        site = view.intent.extras.getParcelable<SiteModel>("site_show")
        view.showSite(site)
    }


}