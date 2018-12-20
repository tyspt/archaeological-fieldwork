package oth.archaeologicalfieldwork.views.sitelist

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.AddOrEditSiteView
import oth.archaeologicalfieldwork.views.site.SiteView

class SiteListPresenter(val view: SiteListView) {
    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun getSite() = app.sites.findAll()

    fun doAddSite() {
        view.startActivityForResult<AddOrEditSiteView>(0)
    }

    fun doShowOneSite(site: SiteModel) {
        view.startActivityForResult(view.intentFor<SiteView>().putExtra("site_show", site), 0)
    }

    fun doEditSite(site: SiteModel) {
        view.startActivityForResult(view.intentFor<SiteView>().putExtra("site_edit", site), 0)
    }

    fun doShowSiteMap() {
        //siteView.startActivity<SiteMapsActivity>()
    }
}