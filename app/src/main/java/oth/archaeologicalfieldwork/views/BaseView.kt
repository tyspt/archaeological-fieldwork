package oth.archaeologicalfieldwork.views

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.models.sites.Location
import oth.archaeologicalfieldwork.models.sites.SiteModel
import oth.archaeologicalfieldwork.views.editlocation.EditLocationView
import oth.archaeologicalfieldwork.views.editsite.AddOrEditSiteView
import oth.archaeologicalfieldwork.views.login.LoginView
import oth.archaeologicalfieldwork.views.map.SiteMapView
import oth.archaeologicalfieldwork.views.settings.SettingsView
import oth.archaeologicalfieldwork.views.sitelist.SiteListView
import oth.archaeologicalfieldwork.views.viewsite.SiteView


val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, ADD_OR_EDIT_SITE, SHOW_SITE, MAPS, LIST, LOGIN, SETTINGS
}

abstract class BaseView : AppCompatActivity(), AnkoLogger {

    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, SiteListView::class.java)
        when (view) {
            VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
            VIEW.ADD_OR_EDIT_SITE -> intent = Intent(this, AddOrEditSiteView::class.java)
            VIEW.SHOW_SITE -> intent = Intent(this, SiteView::class.java)
            VIEW.MAPS -> intent = Intent(this, SiteMapView::class.java)
            VIEW.LIST -> intent = Intent(this, SiteListView::class.java)
            VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
            VIEW.SETTINGS -> intent = Intent(this, SettingsView::class.java)
        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }


    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar, upEnabled: Boolean) {
        toolbar.title = title

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
    }

    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    open fun showSiteInformation(site: SiteModel) {}
    open fun displaySiteImages(site: SiteModel) {}
    open fun showSites(sites: List<SiteModel>?) {}
    open fun updateLocation(location: Location) {}
    open fun showProgress(boolean: Boolean) {}
}