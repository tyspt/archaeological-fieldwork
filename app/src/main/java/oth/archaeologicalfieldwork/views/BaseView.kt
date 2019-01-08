package oth.archaeologicalfieldwork.views

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.editsite.AddOrEditSiteView
import oth.archaeologicalfieldwork.views.site.SiteView
import oth.archaeologicalfieldwork.views.sitelist.SiteListView


val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, ADD_OR_EDIT_SITE, SHOW_SITE, MAPS, LIST
}

abstract class BaseView : AppCompatActivity(), AnkoLogger {

    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, SiteListView::class.java)
        when (view) {
            //VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
            VIEW.ADD_OR_EDIT_SITE -> intent = Intent(this, AddOrEditSiteView::class.java)
            VIEW.SHOW_SITE -> intent = Intent(this, SiteView::class.java)
            //VIEW.MAPS -> intent = Intent(this, PlacemarkMapView::class.java)
            VIEW.LIST -> intent = Intent(this, SiteListView::class.java)
            //TODO Navigation options
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

    fun init(toolbar: Toolbar) {
        toolbar.title = title
        setSupportActionBar(toolbar)
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
    open fun showSites(sites: List<SiteModel>) {}

    open fun showProgress() {}
    open fun hideProgress() {}

}