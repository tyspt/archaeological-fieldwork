package oth.archaeologicalfieldwork.models.room

import android.content.Context
import androidx.room.Room
import oth.archaeologicalfieldwork.models.FieldModel
import oth.archaeologicalfieldwork.models.FieldStore

class FieldStoreRoom(val context: Context) : FieldStore {

    var dao: FieldDao

    init {
        val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
            .fallbackToDestructiveMigration()
            .build()
        dao = database.fieldDao()
    }

    override suspend fun findAll(): List<FieldModel> {
        val deferredFields = async({
            dao.findAll()
        })
        val fields = deferredFields.await()
        return fields
    }

    override suspend fun findById(id: Long): FieldModel? {
        val deferredField = async({
            dao.findById(id)
        })
        val field = deferredField.await()
        return field
    }

    override suspend fun create(field: FieldModel) {
        async({
            dao.create(field)
        })
    }

    override suspend fun update(field: FieldModel) {
        async({
            dao.update(field)
        })
    }

    override suspend fun delete(field: FieldModel) {
        async({
            dao.deleteField(field)
        })
    }

    override fun clear() {
    }
}