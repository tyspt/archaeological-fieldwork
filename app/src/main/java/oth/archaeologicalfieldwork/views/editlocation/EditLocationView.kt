package oth.archaeologicalfieldwork.views.editlocation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_edit_location.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.views.BaseView

class EditLocationView : BaseView(), GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener, AnkoLogger {

    lateinit var map: GoogleMap
    lateinit var presenter: EditLocationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_location)

        init(toolbar_edit_location, true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        presenter = EditLocationPresenter(this)

        mapFragment.getMapAsync {
            map = it
            map.setOnMarkerDragListener(this)
            map.setOnMarkerClickListener(this)
            presenter.doConfigureMap(map)
        }

        toolbar_edit_location.setNavigationOnClickListener {
            presenter.doCancel()
        }
    }

    override fun onMarkerDragEnd(marker: Marker) {}

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doUpdateMarker(marker)
        return false
    }

    override fun onMarkerDrag(marker: Marker) {
        presenter.doUpdateLocation(marker.position.latitude, marker.position.longitude)
        presenter.doUpdateMarker(marker)
    }

    override fun onBackPressed() {
        presenter.doCancel()
    }

    override fun onMarkerDragStart(marker: Marker) {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save_only, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> presenter.doSave(map.cameraPosition.zoom)
        }
        return super.onOptionsItemSelected(item)
    }
}