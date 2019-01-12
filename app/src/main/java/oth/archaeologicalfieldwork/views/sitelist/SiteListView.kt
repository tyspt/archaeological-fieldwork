package oth.archaeologicalfieldwork.views.sitelist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sitelist.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.R.id.menu_favorite_sites_only
import oth.archaeologicalfieldwork.R.id.menu_show_all_sites
import oth.archaeologicalfieldwork.models.sites.SiteModel
import oth.archaeologicalfieldwork.views.BaseView

class SiteListView : BaseView(), AnkoLogger, SiteClickListener {

    lateinit var presenter: SiteListPresenter
    lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sitelist)
        init(toolbar_main, false)
        toolbar_main.title = resources.getString(R.string.title_all_sites)

        info("Sites List Activity started..")
        presenter = initPresenter(SiteListPresenter(this)) as SiteListPresenter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        if (presenter.doCheckSessionInvalid()) {
            presenter.doLogout()
        } else {
            showSites(presenter.getSites())
        }
        btn_add_list.setOnClickListener {
            presenter.doAddSite()
        }
    }

    override fun showSites(sites: List<SiteModel>?) {
        if (sites != null) {
            recyclerView.adapter = SiteAdapter(sites, this)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onSiteClick(site: SiteModel) {
        info("Site ${site.id}: ${site.description} Clicked")
        presenter.doShowOneSite(site)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_sites_map -> presenter.doShowSiteMap()
            R.id.menu_settings -> presenter.doShowSettings()
            R.id.menu_logout -> presenter.doLogout()
            R.id.menu_favorite_sites_only -> this.showFavoriteSites()
            R.id.menu_show_all_sites -> this.showAllSites()
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadAllSites()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showFavoriteSites() {
        toolbar_main.title = resources.getString(R.string.title_favorite_sites)
        menu.findItem(menu_favorite_sites_only).isVisible = false
        menu.findItem(menu_show_all_sites).isVisible = true
        showSites(presenter.getSites()?.filter { it.isFavorite })
    }

    private fun showAllSites() {
        toolbar_main.title = resources.getString(R.string.title_all_sites)
        menu.findItem(menu_favorite_sites_only).isVisible = true
        menu.findItem(menu_show_all_sites).isVisible = false
        showSites(presenter.getSites())
    }
}
