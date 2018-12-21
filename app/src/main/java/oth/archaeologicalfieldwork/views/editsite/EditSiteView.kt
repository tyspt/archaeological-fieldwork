package oth.archaeologicalfieldwork.views.editsite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_site_view.*
import kotlinx.android.synthetic.main.content_edit_site.*
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.models.SiteModel

class EditSiteView : AppCompatActivity() {

    lateinit var presenter: EditSitePresenter
    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_site_view)

        setSupportActionBar(toolbar_edit_site)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter = EditSitePresenter(this)
    }


    fun fetchSiteInformation(site: SiteModel) {
        this.site = site

        site_title.setText(site.title)
        site_description.setText(site.description)

        val gallery: LinearLayout = findViewById(R.id.site_image_gallery)

        for (i in 1..6) {
            val view = layoutInflater.inflate(R.layout.image_gallery_item, gallery, false)
            val imageView = view.findViewById<ImageView>(R.id.site_image)
            imageView.setImageResource(R.drawable.logo)
            gallery.addView(view)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.site.title = site_title.text.toString()
        this.site.description = site_description.text.toString()

        when (item.itemId) {
            R.id.menu_save -> presenter.doAddOrSaveSite(site)
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
