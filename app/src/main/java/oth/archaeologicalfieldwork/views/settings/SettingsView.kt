package oth.archaeologicalfieldwork.views.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.main.MainApp
import oth.archaeologicalfieldwork.views.BaseView


class SettingsView : BaseView() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)
        init(toolbar_settings, true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }
}


class SettingsFragment : PreferenceFragmentCompat(), AnkoLogger {
    lateinit var app: MainApp

    lateinit var prefUsername: EditTextPreference
    lateinit var prefPassword: EditTextPreference
    lateinit var prefStatistics: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey)

        app = activity!!.application as MainApp

        prefUsername = preferenceManager.findPreference("change_name_key") as EditTextPreference
        prefPassword = preferenceManager.findPreference("change_password_key") as EditTextPreference
        prefStatistics = preferenceManager.findPreference("statistics_key") as Preference

        prefUsername.setDefaultValue(app.session.getUsername())
        prefUsername.summary = app.session.getUsername()

        prefPassword.setDefaultValue(app.session.getPassword())
        prefPassword.summary = app.session.getPassword()

        prefStatistics.summary = activity?.resources?.getString(
            R.string.pref_summary_statistics,
            app.sites.findAll().size.toString(),
            app.sites.findAll().count {
                it.hasVisited
            }.toString()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        prefUsername.setOnPreferenceChangeListener { preference, newValue ->
            preference.summary = newValue.toString()
            true
        }

        prefPassword.setOnPreferenceChangeListener { preference, newValue ->
            preference.summary = newValue.toString()
            true
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
