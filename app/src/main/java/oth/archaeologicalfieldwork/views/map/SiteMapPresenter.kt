package oth.archaeologicalfieldwork.views.map

import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW


class SiteMapPresenter(view: BaseView) : BasePresenter(view) {

    fun doPopulateMap(map: GoogleMap) {
        map.uiSettings.isZoomControlsEnabled = true

        val markers = ArrayList<Marker>()
        val sites = app.sites.findAll()

        sites.forEach {
            if (it.location.lng != 0.0) {
                val loc = LatLng(it.location.lat, it.location.lng)
                val options = MarkerOptions().title(it.title).position(loc)
                val marker = map.addMarker(options)
                marker.tag = it
                markers.add(marker)
            }
        }

        if (markers.size > 1) {
            val builder = LatLngBounds.Builder()
            for (marker in markers) {
                builder.include(marker.position)
            }
            val bounds = builder.build()
            val padding = 300 // offset from edges of the map in pixels
            val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            map.animateCamera(cu)
        }
    }

    fun doMarkerSelected(marker: Marker) {}

    fun doInfoWindowClicked(marker: Marker) {
        val site = marker.tag as SiteModel
        view?.navigateTo(VIEW.SHOW_SITE, 0, "site_show", site)
    }

    override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        view?.showSites(app.sites.findAll())
    }
}
