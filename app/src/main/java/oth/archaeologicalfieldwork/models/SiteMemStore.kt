package oth.archaeologicalfieldwork.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class SiteMemStore : SiteStore, AnkoLogger {

    private val sites = ArrayList<SiteModel>()

    override fun findAll(): List<SiteModel> {
        return sites
    }

    override fun create(site: SiteModel) {
        site.id = getId()
        sites.add(site)
        logAll()
    }

    override fun update(site: SiteModel) {
        var foundSite: SiteModel? = sites.find { p -> p.id == site.id }
        if (foundSite != null) {
            foundSite.title = site.title
            foundSite.description = site.description
        }
    }

    internal fun logAll() {
        sites.forEach { info("$it") }
    }

    override fun delete(site: SiteModel) {
        sites.remove(site)
    }

    override fun findById(id: Long): SiteModel? {
        return sites.find { it.id == id }
    }
}