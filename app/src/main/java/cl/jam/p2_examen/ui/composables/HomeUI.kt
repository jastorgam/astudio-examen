package cl.jam.p2_examen.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.jam.p2_examen.ui.vm.MeasurementViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeUI(
    navToNewMeasurement: () -> Unit = {},
    vm: MeasurementViewModel = viewModel(factory = MeasurementViewModel.Factory)
) {

    Scaffold(topBar = {
        TopBar(
            title = "Listado de Mediciones",
            onButtonAddClicked = navToNewMeasurement,
            showBackButton = false,
            showAddButton = true
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navToNewMeasurement() },
            shape = CircleShape,
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(vm.getMeasurements()) { m ->
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "txtDeleteProduct",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(text = m.meterType)
                        Text(m.value.toString())
                        Text(m.date.toString())
                    }
                }
            }
        }
    }
}


