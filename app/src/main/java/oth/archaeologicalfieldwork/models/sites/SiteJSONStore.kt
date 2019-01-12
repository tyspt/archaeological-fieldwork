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

            /* adding default sites to the list  */
            sites.add(
                SiteModel(
                    id = 665,
                    title = "IR0665 Carn Tighernagh, Cork",
                    description = "Oval contour fort of approximately 5.4ha in commanding position, overlooking the Blackwater and Bride river valleys at the eastern edge of the Nagle Mountains. Bivallate for the entire circuit with additional earthworks at possible original entrance to the WNW. Interior heavily overgrown. A large cairn is positioned at the highest point in the interior. A Food Vessel burial was identified upon the partial removal of the stones in 1832 (Borlase 1897, 13). No evidence for other internal features on the surface. The inner enclosing elements survive well throughout the majority of its circuit. It comprises a dump stone bank, maximum 1.6m in height. The outer enclosing feature is best preserved to the WSW and is of similar composition. The additional earthworks at the WNW entrance comprises 2 - 3 earthen banks and corresponding external ditches.",
                    location = Location(lng = -8.28290453297431, lat = 52.11497251)
                )
            )
            sites.add(
                SiteModel(
                    id = 673,
                    title = "IR0673 Hughstown, Kildare",
                    description = "Cropmarks reveal a large, multiple enclosure surrounding the summit of Carrigeen Hill. Hughstwon hillfort is one of up to nine hillfort in a cluster surrounding the town of Baltinglass, Co. Wicklow. Roughly oval in plan, the hillfort has a total site footprint of 8.2ha. It is situated at the SW edge of the Wicklow Mountains in a commanding position, with panoramic views in all directions. Geophysical survey (O'Driscoll 2012) has revealed a complex system of enclosing elements. This includes an outer bank and ditch which surrounds a series of up to four closely set palisade features. The latter may have originally comprised two enclosures, each consisting of a bank (and possible palisade) with external ditch. The strong readings from the gradiometer survey imply that these features were destroyed by fire. Some possible hut structures were recorded by the geophysical survey. No entrance features were identified. At the summit of the hillfort is an extant enclosure. The bank of this enclosure comprises a circular band of large boulders approximately 55m in diameter. Near the centre of this enclosure, a circular bank of grass covered stones 18m in diameter may be the remains of a hut structure. Geophysical survey by O'Driscoll in 2012. LiDAR survey undertaken by Ordnance Survey Ireland. The Irish Army Air Corp and Cambridge aerial photographs reveal two enclosing elements as parch-marks, separated by 23 _ 48m.",
                    location = Location(lng = -6.750001056, lat = 52.9445477)
                )
            )
            sites.add(
                SiteModel(
                    id = 677,
                    title = "IR0677 Toor More, Kilkenny",
                    description = "Large, widely spaced multivallate enclosure surrounding the summit of Corrandhu Hill, one of three summits in an N - S running ridge overlooking Ballyraggett town. This ridge sits on the W edge of the Castlecomer Hills. The hillfort is draped over the narrow ridge, with the concentric enclosing elements ignoring the steep contours to the E and W of the hill, forming a circle in plan. The site has a total footprint of 8.9ha. Bivallate for its entire circuit with three possible original entrances at NNW and SE identified through geophysics (O'Driscoll 2013). No evidence for any internal features on the surface, pits and a possible hut structure identified in geophysics. Enclosing elements survive as low-rise bank features. Targeted excavation  by O'Brien (2013) and geophysical survey by O'Driscoll (2013). Late Bronze Age radiocarbon dates from ditch deposits and palisade features. First written reference by Reverend Carrigan (1905).",
                    location = Location(lng = -7.295006674, lat = 52.78793245)
                )
            )
            sites.add(
                SiteModel(
                    id = 680,
                    title = "IR0680 Ballylin, Limerick",
                    description = "Widely spaced multiple enclosure in commanding position on Ballylin Hill. The hilltop is positioned on partial spur in a N - S running escarpment. This provides Ballylin with panoramic views of the Limerick plains and Shannon estury to the N, E and S. The hillfort has a total site footprint of approximately 20.5ha. Bivallate for its entire circuit with a slight in-turned entrance to the SE. There are two other breaks in the inner enclosing elements which may be original. There are no obvious corresponding breaks in the outer enclosing features. There is no evidence for any internal features on the surface. Geophysical survey has identified a cluster of possible pit features within the inner enclosing elements at the E. The enclosing elements survive well throughout much of its length, however, they are heavily overgrown in places and are under approximately 0.3m of peat. The entire hillfort is under a heavy blanket of heather and patches of scrub. First recorded by John Danaher during aerial photography survey in 1981. Geophysical survey by O'Driscoll (2012). Targeted excavation by O'Brien (2012). Earthwork survey by Cody (1981). Late Bronze Age dates from basal fill of inner and outer enclosing elements.",
                    location = Location(lng = -9.093648929, lat = 52.5020311)
                )
            )
            sites.add(
                SiteModel(
                    id = 685,
                    title = "IR0685 Lisbane, Limerick",
                    description = "Circular multiple enclosure on summit of Kilbradran Hill. This hilltop dominates the surrounding flat, low-lying terrain. The summit offers panoramic views of the surrounding landscape. The hillfort has a total site footprint of 0.4ha. Bivallate for most of its circuit, the NE and E section of the enclosing elements have been truncated by a Post-Medieval road. Possible annex to the N recorded by Westropp is now destroyed (1916-1917, 29). Four possible original entrances consisting of two simple gaps in the inner bank and two simple gaps in the outer enclosing elements. No evidence for any internal features on the surface. Ramparts survive well to N, S and W with some tree cover on the banks. First map depiction in first edition Ordnance Survey mapping. More detailed earthwork survey in second edition Ordnance Survey maps. Earthwork survey by Westropp in 1916.",
                    location = Location(lng = -9.049280026, lat = 52.5439061192725)
                )
            )
            sites.add(
                SiteModel(
                    id = 690,
                    title = "IR0690 Brodullagh South, Mayo",
                    description = "Multiple enclosure on S shoulder of hilltop, surrounding summit and overlooking Black River to E. The hillfort has a total foot print of approximately 2.3ha. Bivallate for most of its circuit with widely spaced enclosing elements on average 40m apart. Ordnance Survey mapping indicates two small gaps in inner enclosing element to N and SSW and three gaps in the outer bank to the N, E and W. It cannot be determined if these are original or later features. A possible hut structure was recorded within the interior of the inner enclosing element. No other information relating to this structure available. Inner enclosing element largely levelled. Outer enclosing element survives well throughout its circuit. Some tree cover to N of interior. First map depiction in first edition Ordnance Survey mapping. More detailed earthwork survey in second edition Ordnance Survey mapping.",
                    location = Location(lng = -9.125060085, lat = 53.50744916)
                )
            )
            sites.add(
                SiteModel(
                    id = 691,
                    title = "IR0691 Commons of Lloyds, Meath",
                    description = "Near the town of Kells, an oval multiple enclosure of approximately 9.3ha total site foot print positioned on a low, conical hill overlooking the River Blackwater. Trivallate for the entire circuit with no recorded entrance features. No evidence for internal features contemporary with the construction or occupation of the hillfort. The outer ramparts are integrated into a field system. The inner and middle enclosing elements are heavily truncated by later agricultural activity. These are visible on aerial photography. Site under pasture. Limited excavations and monitoring by Neary (2003) and Wallace (2003) prior to development. These remain unpublished. Unpublished geophysical survey by The Discovery Programme in 2013. The area is mentioned in the Early Medieval text, ÇThe T_in\u0090, as being the site of a temporary camp (they spent a single night here) for queen Maeve\u0090s army, who were marching from Connaught to Cooley (Carr 1999, 101). First map depiction in first edition Ordnance Survey mapping shows the fort as an oval boundary. Also depicted as such in second edition Ordnance Survey maps.",
                    location = Location(lng = -6.905068405, lat = 53.73319471)
                )
            )
            sites.add(
                SiteModel(
                    id = 696,
                    title = "IR0696 Cumber Lower, Offaly",
                    description = "Large multiple enclosure in commanding position surrounding summit of a steep hilltop, at E edge of Slieve Bloom Mountains. Site overlooks Camcor River to N and is positioned 1.9km to the NE of Ballymacmurragh hillfort. Most likely bivallate for the entire circuit. The total site foot print is approximately 12.2ha. There are no recorded breaks in the enclosing elements. No evidence for any internal features on the surface. Ramparts are largely obscured due to dense tree and scrub cover. Limited site description by Cooke (1875, 154). S section of inner enclosing elements recorded as a field boundary in second edition Ordnance Survey mapping.",
                    location = Location(lng = -7.698853189, lat = 53.0823791)
                )
            )
            sites.add(
                SiteModel(
                    id = 709,
                    title = "IR0709 Curraghadobbin, Tipperary",
                    description = "Multiple enclosure surrounding the summit and western facing slopes of Curraghadobbin Hill, at centre of E - W running escarpment. Likely to be bivallate for its entire length, although this cannot be confirmed due to destruction of the outer enclosing elements by modern forestry plantation. The hillfort has a total site foot print of approximately 4.1ha. There are no recorded entrance features. An oval cairn is positioned at the center of the hillfort interior. The inner enclosing elements have been heavily damaged by forestry clearance and its subsequent re-setting. The outer enclosing elements have been incorporated into later field boundaries or been destroyed. Earliest detection by Irish Army Air Corps and subsequent detection by GSI in 1973.",
                    location = Location(lng = -7.45808088, lat = 52.40781669)
                )
            )
            sites.add(
                SiteModel(
                    id = 718,
                    title = "IR0718 Brusselstown Ring, Wicklow",
                    description = "Multiple enclosure surrounding the SE flat-topped summit of Spinans Hill at SW edge of Wicklow mountains, with panoramic views. Brusselstown Ring is one of nine hillforts comprising the Baltinglass hillfort cluster. It has a total footprint of 32.43ha making it one of the largest hillforts in Ireland. The inner enclosing elements forms a complete circuit. The outer has been damaged at the SW but likely formed a complete circuit. The are four possible original entrances comprising simple breaks in the inner bank at the N, E, S and W. There are no apparent entrance features in the outer enclosing elements. Two structures have been recorded within the inner enclosure. Twenty three structures immediately outside the inner enclosing feature have also been identified (Grogan and Kilfeather 1997, 41). Internal enclosing elements survives almost intact. The outer has been damaged at SW and is covered by grass and heather for much of its circuit. Interior under grassland. Earliest identified map depiction in first edition Ordnance Survey 6 inch mapping. Monument survey by Condit (1992). Monument survey by Grogan and Kilfeather (1996, 25). Aerial photographs by the Cambridge aerial survey unit and GSI.",
                    location = Location(lng = -6.616450004, lat = 52.9629498)
                )
            )
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
