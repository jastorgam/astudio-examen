package cl.jam.p2_examen

import android.app.Application
import androidx.room.Room
import cl.jam.p2_examen.data.MeasurementDatabase

class MeasurementApplication : Application() {
    val db by lazy {
        Room.databaseBuilder(this, MeasurementDatabase::class.java, "MeasurementData.db").build()
    }
    val measurementDao by lazy { db.measurementDao() }

}