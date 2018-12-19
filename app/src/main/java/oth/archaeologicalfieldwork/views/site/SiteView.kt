package oth.archaeologicalfieldwork.views.site

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_site.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.models.SiteModel

class SiteView : AppCompatActivity(), AnkoLogger {

    lateinit var presenter: SitePresenter
    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_site)
        //toolbarAdd.title = title
        //setSupportActionBar(toolbarAdd)

        presenter = SitePresenter(this)


    }

    fun showSite(site: SiteModel) {
        siteTitle.text = site.title
        siteDescription.text = site.description
    }

}