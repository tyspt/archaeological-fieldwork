package oth.archaeologicalfieldwork.models

interface FieldStore {
    suspend fun findAll(): List<FieldModel>
    suspend fun findById(id: Long): FieldModel?
    suspend fun create(field: FieldModel)
    suspend fun update(field: FieldModel)
    suspend fun delete(field: FieldModel)
    fun clear()
}