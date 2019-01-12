package oth.archaeologicalfieldwork.models.users

interface UserStore {
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun findByName(username: String?): UserModel?
}