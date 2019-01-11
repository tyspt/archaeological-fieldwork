package oth.archaeologicalfieldwork.views.login

import org.jetbrains.anko.AnkoLogger
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
            user = UserModel(email = mEmail, password = mPassword)
            app.users.create(user)
            result = true
        }
        app.session.setUsername(user.email)
        app.session.setPassword(user.password)
        return result
    }


}