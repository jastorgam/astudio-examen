package cl.jam.p2_examen.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cl.jam.p2_examen.PostApplication
import cl.jam.p2_examen.data.PostDao
import cl.jam.p2_examen.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(val postDao: PostDao) : ViewModel() {

    var postes by mutableStateOf(listOf<Post>())

    fun deleteTable() {
        viewModelScope.launch(Dispatchers.IO) {
            postDao.deleteTable()
        }
        getPosts()
    }


    fun getPosts(): List<Post> {
        viewModelScope.launch(Dispatchers.IO) {
            postes = postDao.getAll()
        }
        return postes;
    }

    fun addPost(Post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            postDao.insert(Post)
        }
        getPosts()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PostApplication)
                PostViewModel(application.postDao)
            }
        }
    }


}