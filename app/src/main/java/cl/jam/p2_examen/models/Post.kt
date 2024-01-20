package cl.jam.p2_examen.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var meterType: String,
    var date: LocalDate,
    var value: Int = 0,
    var creationDate: LocalDate = LocalDate.now()
) {

}