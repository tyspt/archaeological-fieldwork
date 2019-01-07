package oth.archaeologicalfieldwork.views.site

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_site.*
import kotlinx.android.synthetic.main.content_show_site.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.models.SiteModel

class SiteView : AppCompatActivity(), AnkoLogger {

    lateinit var presenter: SitePresenter
    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_site)

        //toolbar_show_site.title = title

        /* show back button */
        setSupportActionBar(toolbar_show_site)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter = SitePresenter(this)
    }


    fun showSite(site: SiteModel) {
        this.site = site

        site_title.text = site.title
        siteDescription.text = site.description

        val gallery: LinearLayout = findViewById(R.id.site_image_gallery)

        for (i in 1..6) {
            val view = layoutInflater.inflate(R.layout.image_gallery_item, gallery, false)
            val imageView = view.findViewById<ImageView>(R.id.site_image)
            imageView.setImageResource(R.drawable.logo)
            gallery.addView(view)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_edit -> presenter.doEditSite(site)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }

}