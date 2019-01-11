package oth.archaeologicalfieldwork.views.login

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import oth.archaeologicalfieldwork.models.users.UserModel
import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

    fun doLoginSuccess() {
        view?.navigateTo(VIEW.LIST)
    }

    fun doUserLoginOrSignUp(mEmail: String, mPassword: String): Boolean {
        val user = app.users.findByName(mEmail)

        if (user != null) {
            info("user exists ->${user.email} / password correct: ${user.password == mPassword}")
            return user.password == mPassword  // Account exists, check if the password matches.
        } else {
            info("create new user ->$mEmail")
            app.users.create(UserModel(email = mEmail, password = mPassword))
        }
        return true
    }
}