package oth.archaeologicalfieldwork.models.users

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.helpers.exists
import oth.archaeologicalfieldwork.helpers.read
import oth.archaeologicalfieldwork.helpers.write
import java.util.*

class UserJSONStore : UserStore, AnkoLogger {
    val JSON_FILE = "users.json"
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    val listType = object : TypeToken<ArrayList<UserModel>>() {}.type

    fun generateRandomId(): Long {
        return Random().nextLong()
    }


    val context: Context
    var users = mutableListOf<UserModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<UserModel> {
        logAll()
        return users
    }

    internal fun logAll() {
        users.forEach { info("$it") }
    }

    override fun create(user: UserModel) {
        user.id = generateRandomId()
        users.add(user)
        serialize()
    }

    override fun update(user: UserModel) {
        val userList = findAll() as ArrayList<UserModel>
        var foundUser = userList.find { p -> p.id == user.id }
        if (foundUser != null) {
            foundUser.email = user.email
            foundUser.password = user.password
            //todo logic in update user
        }
        serialize()
    }

    override fun findById(id: Long): UserModel? {
        return users.find { it.id == id }
    }

    override fun findByName(username: String): UserModel? {
        return users.find { it.email == username }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        users = Gson().fromJson(jsonString, listType)
    }
}