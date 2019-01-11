package oth.archaeologicalfieldwork.views.editlocation

import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.checkLocationPermissions
import oth.archaeologicalfieldwork.helpers.isPermissionGranted
import oth.archaeologicalfieldwork.models.sites.Location
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import kotlin.math.abs

class EditLocationPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

    var locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view)
    var editLocationView = view as EditLocationView

    var location = Location()

    private var marker: Marker? = null

    private val defaultLocation = Location(49.003571, 12.095591, 15f)

    init {
        location = view.intent.extras.getParcelable("location")

        if (abs(location.lng) < 0.0001) {
            if (checkLocationPermissions(view)) {
                doSetCurrentLocation() // get the current location
            } else {
                doUpdateLocation(defaultLocation.lat, defaultLocation.lng) // if no permission, use the default location
            }
        }
    }

    fun doAddMarker(map: GoogleMap) {
        val loc = LatLng(location.lat, location.lng)
        val options = MarkerOptions()
            .title("Site Location")
            .snippet("GPS : " + loc.toString())
            .draggable(true)
            .position(loc)
        marker = map.addMarker(options)
        doUpdateMarkerSnippet(marker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
        info("location: add marker called")
    }

    @SuppressLint("MissingPermission")
    fun doSetCurrentLocation() {
        locationService.lastLocation.addOnSuccessListener {
            doUpdateLocation(it.latitude, it.longitude)
            doUpdateMarkerPosition(marker)
            doUpdateMarkerSnippet(marker)
        }
    }

    override fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (isPermissionGranted(requestCode, grantResults)) {
            doSetCurrentLocation()  // get the current location
        } else {
            doUpdateLocation(
                defaultLocation.lat,
                defaultLocation.lng
            ) // permissions denied, so use the default location
            doUpdateMarkerPosition(marker)
            doUpdateMarkerSnippet(marker)
        }
    }

    fun doUpdateLocation(lat: Double, lng: Double) {
        location.lat = "%.6f".format(lat).toDouble()
        location.lng = "%.6f".format(lng).toDouble()
        info("doUpdateLocation: : $location")
    }


    fun doSave(zoom: Float) {
        location.zoom = zoom
        val resultIntent = Intent()
        resultIntent.putExtra("location", location)
        view?.setResult(0, resultIntent)
        view?.finish()
    }

    fun doUpdateMarkerSnippet(marker: Marker?) {
        if (marker != null && abs(location.lng) > 0.0001) {
            val loc = LatLng(location.lat, location.lng)
            marker.snippet = "GPS : " + loc.toString()
            marker.showInfoWindow()
            info("marker updated: $marker || $location")
        } else {
            info("marker not updated: $marker || $location")
        }
    }

    fun doUpdateMarkerPosition(marker: Marker?) {
        val loc = LatLng(location.lat, location.lng)
        marker?.position = loc
        editLocationView.map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
    }
}