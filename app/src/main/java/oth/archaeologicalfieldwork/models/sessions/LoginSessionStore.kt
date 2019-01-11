package oth.archaeologicalfieldwork.models.sessions

import oth.archaeologicalfieldwork.models.users.UserModel

interface LoginSessionStore {
    fun addToSession(user: UserModel)
    fun removeFromSession(user: UserModel)
}