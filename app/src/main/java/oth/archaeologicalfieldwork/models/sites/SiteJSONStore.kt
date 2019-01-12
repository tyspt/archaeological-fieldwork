package oth.archaeologicalfieldwork.models.sites

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.exists
import oth.archaeologicalfieldwork.helpers.read
import oth.archaeologicalfieldwork.helpers.write
import oth.archaeologicalfieldwork.main.MainApp
import java.util.*

class SiteJSONStore(val context: Context) : SiteStore, AnkoLogger {
    var JSON_FILE = ""
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    val listType = object : TypeToken<java.util.ArrayList<SiteModel>>() {}.type

    fun generateRandomId(): Long {
        return Random().nextLong()
    }

    var sites = mutableListOf<SiteModel>()

    init {
        val username = (context as MainApp).session.getUsername()
        JSON_FILE = "sites_$username.json"

        if (exists(context, JSON_FILE)) {
            info("JSON $JSON_FILE exists")
            deserialize()
        } else {
            info("JSON $JSON_FILE not exist")
        }
    }

    override fun findAll(): MutableList<SiteModel> {
        logAll()
        return sites
    }

    internal fun logAll() {
        sites.forEach { info("$it") }
    }

    override fun create(site: SiteModel) {
        site.id = generateRandomId()
        sites.add(site)
        serialize()
    }

    override fun update(site: SiteModel) {
        val sitesList = findAll() as ArrayList<SiteModel>
        var foundSite: SiteModel? = sitesList.find { p -> p.id == site.id }
        if (foundSite != null) {
            foundSite.title = site.title
            foundSite.description = site.description
            foundSite.hasVisited = site.hasVisited
            foundSite.visitDate = site.visitDate
            foundSite.isFavorite = site.isFavorite
            foundSite.images = site.images
            foundSite.location = site.location
        }
        serialize()
    }

    override fun delete(site: SiteModel) {
        sites.remove(site)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(sites, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        sites = Gson().fromJson(jsonString, listType)
    }

    override fun findById(id: Long): SiteModel? {
        val foundSite: SiteModel? = sites.find { it.id == id }
        return foundSite
    }
}
