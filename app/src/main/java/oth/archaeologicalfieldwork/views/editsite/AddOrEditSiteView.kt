package oth.archaeologicalfieldwork.views.editsite

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_edit_site.*
import kotlinx.android.synthetic.main.content_edit_site.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.readImageFromPath
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.views.BaseView
import java.text.SimpleDateFormat
import java.util.*


class AddOrEditSiteView : BaseView(), AnkoLogger {

    lateinit var presenter: AddOrEditSitePresenter
    var site = SiteModel()

    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_site)

        // supports the funciton of Android default Backbutton
        setSupportActionBar(toolbar_edit_site)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter = AddOrEditSitePresenter(this)

        radio_group_has_visited.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_visited) {
                visit_date_edit.visibility = View.VISIBLE
            } else {
                visit_date_edit.visibility = View.GONE
            }
        }

        choose_image.setOnClickListener { presenter.doSelectImage() }

        /* Visit Date Picker   */
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "yyyy/MM/dd"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            visit_date_edit.setText(sdf.format(cal.time))
        }

        visit_date_edit.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun showSiteInformation(site: SiteModel) {
        this.site = site

        site_title_edit.setText(site.title)
        site_description_edit.setText(site.description)

        if (site.hasVisited) {
            radio_group_has_visited.check(R.id.radio_visited)
            visit_date_edit.visibility = View.VISIBLE
            visit_date_edit.setText(site.visitDate)
        }

        displaySiteImages(site)
    }

    override fun displaySiteImages(site: SiteModel) {
        this.site.images = site.images
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
        this.site.title = site_title_edit.text.toString()
        this.site.description = site_description_edit.text.toString()
        this.site.hasVisited = (radio_visited.id == radio_group_has_visited.checkedRadioButtonId)
        this.site.visitDate = visit_date_edit.text.toString()

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
