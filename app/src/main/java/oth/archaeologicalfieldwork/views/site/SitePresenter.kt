package oth.archaeologicalfieldwork.views.site

import android.content.Intent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW

class SitePresenter(view: BaseView) : BasePresenter(view), AnkoLogger {
    val SITE_EDIT = 1

    var site = SiteModel()

    init {
        if (view.intent.hasExtra("site_show")) {
            site = view.intent.extras.getParcelable("site_show")
            view.showSiteInformation(site)
            info("site-show opened")
        }
    }

    fun doEditSite(site: SiteModel) {
        view?.navigateTo(VIEW.ADD_OR_EDIT_SITE, SITE_EDIT, "site_edit", site)
    }

    fun doDeleteSite(site: SiteModel) {
        app.sites.delete(site)
        view?.finish()
    }

    override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            SITE_EDIT -> {
                site = data.extras.getParcelable("changed_site")
                view?.showSiteInformation(site)
            }
        }
    }
}