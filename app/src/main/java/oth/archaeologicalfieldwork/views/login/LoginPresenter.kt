package oth.archaeologicalfieldwork.views.login

import oth.archaeologicalfieldwork.views.BasePresenter
import oth.archaeologicalfieldwork.views.BaseView
import oth.archaeologicalfieldwork.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {


    fun doLoginSuccess() {
        view?.navigateTo(VIEW.LIST)
    }
}