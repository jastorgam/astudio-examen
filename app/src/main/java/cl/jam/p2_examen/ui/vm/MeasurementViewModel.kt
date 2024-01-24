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
import cl.jam.p2_examen.MeasurementApplication
import cl.jam.p2_examen.data.MeasurementDao
import cl.jam.p2_examen.models.Measurement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeasurementViewModel(val measurementDao: MeasurementDao) : ViewModel() {

    var measurementList by mutableStateOf(listOf<Measurement>())

    fun deleteTable() {
        viewModelScope.launch(Dispatchers.IO) {
            measurementDao.deleteTable()
        }
        getMeasurements()
    }


    fun getMeasurements(): List<Measurement> {
        viewModelScope.launch(Dispatchers.IO) {
            measurementList = measurementDao.getAll()
        }
        return measurementList;
    }

    fun addMeasurement(Measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            measurementDao.insert(Measurement)
        }
        getMeasurements()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MeasurementApplication)
                MeasurementViewModel(application.measurementDao)
            }
        }
    }


}