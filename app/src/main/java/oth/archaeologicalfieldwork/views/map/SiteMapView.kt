package oth.archaeologicalfieldwork.views.map

import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_site_map.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.models.sites.SiteModel
import oth.archaeologicalfieldwork.views.BaseView

class SiteMapView : BaseView(), GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, AnkoLogger {
    lateinit var presenter: SiteMapPresenter
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_map)
        super.init(toolbar_site_map, true)

        presenter = initPresenter(SiteMapPresenter(this)) as SiteMapPresenter

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync {
            map = it
            map.setOnMarkerClickListener(this)
            map.setOnInfoWindowClickListener(this)
            presenter.doPopulateMap(map)
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doMarkerSelected(marker)
        info("marker clicked")
        return false
    }

    override fun onInfoWindowClick(marker: Marker) {
        presenter.doInfoWindowClicked(marker)
        info("info window clicked")
    }

    override fun showSites(sites: List<SiteModel>?) {
        presenter.doPopulateMap(map)
    }
}