package oth.archaeologicalfieldwork.views.sitelist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sitelist.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.models.SiteModel

class SiteListView : AppCompatActivity(), AnkoLogger, SiteClickListener {

    lateinit var presenter: SiteListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sitelist)
        //toolbarMain.title = title
        //setSupportActionBar(toolbarMain)

        info("Sites List Activity started..")

        presenter = SiteListPresenter(this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = SiteAdapter(presenter.getSite(), this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSiteClick(site: SiteModel) {
        info("Site ${site.id}: ${site.description} Clicked")
        presenter.doShowOneSite(site)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //R.id.item_add -> presenter.doAddPlacemark()
            //R.id.item_map -> presenter.doShowPlacemarksMap()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
