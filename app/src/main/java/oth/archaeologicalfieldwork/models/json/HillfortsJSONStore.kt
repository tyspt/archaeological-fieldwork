package oth.archaeologicalfieldwork.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.helpers.exists
import oth.archaeologicalfieldwork.helpers.read
import oth.archaeologicalfieldwork.helpers.write
import oth.archaeologicalfieldwork.models.HillfortModel
import oth.archaeologicalfieldwork.models.HillfortStore
import java.util.*

val JSON_FILE = "hillforts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HillfortModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HillfortJSONStore : HillfortStore, AnkoLogger {

    val context: Context
    var hillforts = mutableListOf<HillfortModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override suspend fun findAll(): MutableList<HillfortModel> {
        return hillforts
    }

    override suspend fun findById(id: Long): HillfortModel? {
        val foundHillfort: HillfortModel? = hillforts.find { it.id == id }
        return foundHillfort
    }

    override suspend fun create(hillfort: HillfortModel) {
        hillfort.id = generateRandomId()
        hillforts.add(hillfort)
        serialize()
    }

    override suspend fun update(hillfort: HillfortModel) {
        val hillfortsList = findAll() as ArrayList<HillfortModel>
        var foundHillfort: HillfortModel? = hillfortsList.find { p -> p.id == hillfort.id }
        if (foundHillfort != null) {
            foundHillfort.title = hillfort.title
            foundHillfort.description = hillfort.description
            foundHillfort.image = hillfort.image
            foundHillfort.location = hillfort.location
        }
        serialize()
    }

    override suspend fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(hillforts, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        hillforts = Gson().fromJson(jsonString, listType)
    }

    override fun clear() {
        hillforts.clear()
    }
}
