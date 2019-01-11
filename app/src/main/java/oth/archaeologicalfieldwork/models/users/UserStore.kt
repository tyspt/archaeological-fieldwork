package oth.archaeologicalfieldwork.models.users

interface UserStore {
    fun findAll(): List<UserModel>
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun findById(id: Long): UserModel?
    fun findByName(username: String): UserModel?
}