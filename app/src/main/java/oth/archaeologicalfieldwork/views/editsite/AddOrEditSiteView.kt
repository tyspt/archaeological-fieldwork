package oth.archaeologicalfieldwork.views.editsite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_site.*
import kotlinx.android.synthetic.main.content_edit_site.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.readImageFromPath
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.addoreditsite.AddOrEditSitePresenter

class AddOrEditSiteView : AppCompatActivity(), AnkoLogger {

    lateinit var presenter: AddOrEditSitePresenter
    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_site)

        // supports the funciton of Android default Backbutton
        setSupportActionBar(toolbar_edit_site)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter = AddOrEditSitePresenter(this)

        choose_image.setOnClickListener { presenter.doSelectImage() }
    }


    fun showSiteInformation(site: SiteModel) {
        site_title.setText(site.title)
        site_description.setText(site.description)

        showSiteImages(site)
    }

    fun showSiteImages(site: SiteModel) {
        site_image_gallery_edit.removeAllViews()

        for (image in site.images) {
            val view = layoutInflater.inflate(R.layout.image_gallery_item, site_image_gallery_edit, false)
            val imageView = view.findViewById<ImageView>(R.id.site_image)
            imageView.setImageBitmap(readImageFromPath(this, image))
            site_image_gallery_edit.addView(view)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.site.title = site_title.text.toString()
        this.site.description = site_description.text.toString()
        this.site.hasVisited = (radio_visited.id == radio_group_has_visited.checkedRadioButtonId)
        this.site.visitDate = visit_date.text.toString()

        when (item.itemId) {
            R.id.menu_save -> presenter.doAddOrSaveSite(site)
            R.id.menu_cancel -> presenter.doCancel()
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
