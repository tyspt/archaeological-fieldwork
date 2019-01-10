package oth.archaeologicalfieldwork.views.editlocation

import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import oth.archaeologicalfieldwork.models.Location
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView

class EditLocationPresenter(view: BaseView) : BasePresenter(view) {

    var location = Location()

    init {
        location = view.intent.extras.getParcelable("location")
    }

    fun doConfigureMap(map: GoogleMap) {
        val loc = LatLng(location.lat, location.lng)
        val options = MarkerOptions()
            .title("Site Location")
            .snippet("GPS : " + loc.toString())
            .draggable(true)
            .position(loc)
        val marker = map.addMarker(options)
        marker.showInfoWindow()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
    }

    fun doUpdateLocation(lat: Double, lng: Double) {
        location.lat = "%.6f".format(lat).toDouble()
        location.lng = "%.6f".format(lng).toDouble()
    }

    fun doSave(zoom: Float) {
        location.zoom = zoom
        val resultIntent = Intent()
        resultIntent.putExtra("location", location)
        view?.setResult(0, resultIntent)
        view?.finish()
    }

    fun doUpdateMarker(marker: Marker?) {
        val loc = LatLng(location.lat, location.lng)
        marker?.snippet = "GPS : " + loc.toString()
        marker?.showInfoWindow()
    }
}