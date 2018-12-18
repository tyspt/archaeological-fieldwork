package oth.archaeologicalfieldwork.models.room

import androidx.room.Database
import androidx.room.RoomDatabase
import oth.archaeologicalfieldwork.models.FieldModel

@Database(entities = arrayOf(FieldModel::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun fieldDao(): FieldDao
}