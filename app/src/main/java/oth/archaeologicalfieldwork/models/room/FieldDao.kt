package oth.archaeologicalfieldwork.models.room

import androidx.room.*
import oth.archaeologicalfieldwork.models.FieldModel

@Dao
interface FieldDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(field: FieldModel)

    @Query("SELECT * FROM FieldModel")
    fun findAll(): List<FieldModel>

    @Query("select * from FieldModel where id = :id")
    fun findById(id: Long): FieldModel

    @Update
    fun update(field: FieldModel)

    @Delete
    fun deleteField(field: FieldModel)
}