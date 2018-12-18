package oth.archaeologicalfieldwork.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.helpers.exists
import oth.archaeologicalfieldwork.helpers.read
import oth.archaeologicalfieldwork.helpers.write
import oth.archaeologicalfieldwork.models.SiteModel
import oth.archaeologicalfieldwork.models.SiteStore
import java.util.*

val JSON_FILE = "sites.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<SiteModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class SiteJSONStore : SiteStore, AnkoLogger {

    val context: Context
    var sites = mutableListOf<SiteModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override suspend fun findAll(): MutableList<SiteModel> {
        return sites
    }

    override suspend fun findById(id: Long): SiteModel? {
        val foundSite: SiteModel? = sites.find { it.id == id }
        return foundSite
    }

    override suspend fun create(site: SiteModel) {
        site.id = generateRandomId()
        sites.add(site)
        serialize()
    }

    override suspend fun update(site: SiteModel) {
        val sitesList = findAll() as ArrayList<SiteModel>
        var foundSite: SiteModel? = sitesList.find { p -> p.id == site.id }
        if (foundSite != null) {
            foundSite.title = site.title
            foundSite.description = site.description
            foundSite.image = site.image
            foundSite.location = site.location
        }
        serialize()
    }

    override suspend fun delete(site: SiteModel) {
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

    override fun clear() {
        sites.clear()
    }
}
