package cl.jam.p2_examen.converters

import android.util.Log
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateConverter {
    val dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return LocalDateTime.parse(value?.toString(), dtf)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.format(dtf)?.toLong()
    }
}