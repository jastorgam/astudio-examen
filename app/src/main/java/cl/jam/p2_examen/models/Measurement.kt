package cl.jam.p2_examen.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Measurement(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var meterType: MeasurementTypes,
    var date: LocalDateTime,
    var value: Int = 0,
    var creationDate: LocalDateTime = LocalDateTime.now()
) {

}