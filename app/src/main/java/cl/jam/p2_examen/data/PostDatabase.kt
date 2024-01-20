package cl.jam.p2_examen.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.jam.p2_examen.converters.LocalDateConverter
import cl.jam.p2_examen.models.Post

@Database(entities = [Post::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}