package oth.archaeologicalfieldwork.views.site

import android.content.Intent
import android.widget.Toast
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R
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

    fun doFlipFavorite(site: SiteModel) {
        site.isFavorite = !site.isFavorite
        app.sites.update(site)
        view?.showSiteInformation(site)

        if (site.isFavorite) {
            Toast.makeText(view, R.string.message_site_set_favorite, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(view, R.string.message_site_unset_favorite, Toast.LENGTH_SHORT).show()
        }
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