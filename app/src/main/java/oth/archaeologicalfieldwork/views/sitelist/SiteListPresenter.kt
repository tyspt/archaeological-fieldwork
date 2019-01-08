package oth.archaeologicalfieldwork.views.sitelist

import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW

class SiteListPresenter(view: BaseView) : BasePresenter(view) {

    fun getSite() = app.sites.findAll()

    fun doAddSite() {
        view?.navigateTo(VIEW.ADD_OR_EDIT_SITE)
        //view?.startActivityForResult<AddOrEditSiteView>(0)
    }

    fun doShowOneSite(site: SiteModel) {
        view?.navigateTo(VIEW.SHOW_SITE, 0, "site_show", site)
        //view.startActivityForResult(view.intentFor<SiteView>().putExtra("site_show", site), 0)
    }

    fun doShowSiteMap() {
        view?.navigateTo(VIEW.MAPS)
        //editSiteView.startActivity<SiteMapsActivity>()
    }

    fun loadSites() {
        view?.showSites(app.sites.findAll())
        // view.showSites(app.sites.findAll())
    }
}