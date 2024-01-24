package cl.jam.p2_examen.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.jam.p2_examen.converters.LocalDateConverter
import cl.jam.p2_examen.models.Measurement

@Database(entities = [Measurement::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class MeasurementDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
}