package cl.jam.p2_examen.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cl.jam.p2_examen.models.Measurement

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM measurement ORDER BY creationDate DESC")
    suspend fun getAll(): List<Measurement>

    @Query("SELECT * FROM measurement WHERE id = :id")
    suspend fun getById(id: Long): Measurement

    @Insert
    suspend fun insert(measurement: Measurement)

    @Update
    suspend fun update(measurement: Measurement)

    @Delete
    suspend fun delete(measurement: Measurement)

    @Query("DELETE FROM Measurement")
    suspend fun deleteTable()
}