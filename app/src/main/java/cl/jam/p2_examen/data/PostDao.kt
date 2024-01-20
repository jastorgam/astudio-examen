package cl.jam.p2_examen.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cl.jam.p2_examen.models.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post ORDER BY creationDate DESC")
    suspend fun getAll(): List<Post>

    @Query("SELECT * FROM post WHERE id = :id")
    suspend fun getById(id: Long): Post

    @Insert
    suspend fun insert(post: Post)

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)

    @Query("DELETE FROM Post")
    suspend fun deleteTable()
}