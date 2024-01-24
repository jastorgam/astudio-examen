package cl.jam.p2_examen.helpers

import java.util.Calendar
import java.util.Date

fun getDate(): String {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    calendar.time = Date()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    return "${year}-${(month + 1).toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
}