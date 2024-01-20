package cl.jam.p2_examen

import android.app.Application
import androidx.room.Room
import cl.jam.p2_examen.data.PostDatabase

class PostApplication : Application() {
    val db by lazy {
        Room.databaseBuilder(this, PostDatabase::class.java, "postdata.db").build()
    }
    val postDao by lazy { db.postDao() }

}