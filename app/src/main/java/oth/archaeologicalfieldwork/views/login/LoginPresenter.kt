package oth.archaeologicalfieldwork.views.login

import org.jetbrains.anko.AnkoLogger
import oth.archaeologicalfieldwork.models.sites.SiteJSONStore
import oth.archaeologicalfieldwork.models.users.UserModel
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {
    fun doLoginSuccess() {
        view?.navigateTo(VIEW.LIST)
    }

    fun doSignupOrLogin(mEmail: String, mPassword: String): Boolean {
        var user = app.users.findByName(mEmail)
        val result: Boolean
        if (user != null) {
            result = (user.password == mPassword)
        } else {
            user = UserModel(username = mEmail, password = mPassword)
            app.users.create(user)
            result = true
        }

        app.session.setUsername(user.username)
        app.session.setPassword(user.password)

        //sites = SiteMemStore()
        app.sites = SiteJSONStore(app.applicationContext)

        return result
    }


}