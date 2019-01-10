package oth.archaeologicalfieldwork.views.site

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_show_site.*
import kotlinx.android.synthetic.main.content_show_site.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.readImageFromPath
import oth.archaeologicalfieldwork.models.Location
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.BaseView

class SiteView : BaseView(), AnkoLogger {

    lateinit var presenter: SitePresenter
    var menu: Menu? = null
    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_site)

        init(toolbar_show_site, true)

        presenter = initPresenter(SitePresenter(this)) as SitePresenter
    }

    override fun showSiteInformation(site: SiteModel) {
        this.site = site
        site_title_show.text = site.title
        site_description_show.text = site.description
        site_visited_checkbox_show.isChecked = site.hasVisited

        if (site.hasVisited) {
            site_visit_date_show.text = resources.getString(R.string.visit_date_text, site.visitDate)
        } else {
            site_visit_date_show.text = resources.getString(R.string.not_visited_text)
        }

        updateLocation(site.location)
        displaySiteImages(site)
        showFavoriteIcon(site)
    }

    private fun showFavoriteIcon(site: SiteModel) {
        if (site.isFavorite) {
            this.menu?.findItem(R.id.menu_favorite)?.setIcon(R.drawable.ic_bookmark_black_24dp)
        } else {
            this.menu?.findItem(R.id.menu_favorite)?.setIcon(R.drawable.ic_bookmark_border_black_24dp)
        }
    }

    override fun displaySiteImages(site: SiteModel) {
        this.site.images = site.images
        site_image_gallery_show.removeAllViews()

        for (image in site.images) {
            val view = layoutInflater.inflate(R.layout.content_image_gallery_item, site_image_gallery_show, false)
            val imageView = view.findViewById<ImageView>(R.id.site_image)
            imageView.setImageBitmap(readImageFromPath(this, image))
            site_image_gallery_show.addView(view)
        }
    }

    override fun updateLocation(location: Location) {
        if (location.lng != 0.0) {
            this.site.location = location
            location_info_text_show.visibility = View.VISIBLE
            location_info_text_show.text = resources.getString(
                R.string.location_text,
                location.lat.toString(),
                location.lng.toString()
            )
        } else {
            location_info_text_show.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_view_site, menu)
        this.menu = menu
        showFavoriteIcon(site)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_edit -> presenter.doEditSite(site)
            R.id.menu_delete -> presenter.doDeleteSite(site)
            R.id.menu_favorite -> presenter.doFlipFavorite(site)
            R.id.menu_navigate -> presenter.doStartNavigation(site)
        }
        return super.onOptionsItemSelected(item)
    }


}