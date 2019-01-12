package oth.archaeologicalfieldwork.views.sitelist

import oth.archaeologicalfieldwork.models.sites.SiteModel
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW

class SiteListPresenter(view: BaseView) : BasePresenter(view) {

    fun getSites() = app.sites?.findAll()

    fun doAddSite() {
        view?.navigateTo(VIEW.ADD_OR_EDIT_SITE)
    }

    fun doShowOneSite(site: SiteModel) {
        view?.navigateTo(VIEW.SHOW_SITE, 0, "site_show", site)
    }

    fun doShowSiteMap() {
        view?.navigateTo(VIEW.MAPS)
    }

    fun loadAllSites() {
        val sites = app.sites?.findAll()
        if (sites != null) {
            view?.showSites(sites)
        }
    }

    fun doLogout() {
        app.session.clearSession()
        app.sites = null
        view?.navigateTo(VIEW.LOGIN)
    }

    fun doShowSettings() {
        view?.navigateTo(VIEW.SETTINGS)
    }

    fun doCheckSessionInvalid(): Boolean {
        return (app.session.getUsername().isNullOrEmpty())
    }
}