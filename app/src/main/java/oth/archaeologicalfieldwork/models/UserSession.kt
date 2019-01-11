package oth.archaeologicalfieldwork.models

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class UserSession(context: Context) {
    var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setUsername(username: String) {
        prefs.edit().putString("username", username).apply()
    }

    fun setPassword(password: String) {
        prefs.edit().putString("password", password).apply()
    }

    fun getUsername(): String? {
        return prefs.getString("username", "")
    }

    fun getPassword(): String? {
        return prefs.getString("password", "")
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}