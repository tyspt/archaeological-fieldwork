package oth.archaeologicalfieldwork.views.site

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_show_site.*
import kotlinx.android.synthetic.main.content_show_site.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.readImageFromPath
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.BaseView

class SiteView : BaseView(), AnkoLogger {

    lateinit var presenter: SitePresenter
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

        displaySiteImages(site)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_edit -> presenter.doEditSite(site)
            R.id.menu_delete -> presenter.doDeleteSite(site)
        }
        return super.onOptionsItemSelected(item)
    }

}