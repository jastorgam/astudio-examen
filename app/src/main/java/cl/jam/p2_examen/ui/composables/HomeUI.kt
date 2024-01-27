package cl.jam.p2_examen.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.jam.p2_examen.R
import cl.jam.p2_examen.models.MeasurementTypes
import cl.jam.p2_examen.ui.vm.MeasurementViewModel
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeUI(
    navToNewMeasurement: () -> Unit = {},
    vm: MeasurementViewModel = viewModel(factory = MeasurementViewModel.Factory)
) {

    LaunchedEffect(Unit) {
        vm.getMeasurements()
    }


    val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")


    Scaffold(topBar = {
        TopBar(
            title = stringResource(id = R.string.measurementList),
            onButtonAddClicked = navToNewMeasurement,
            showBackButton = false,
            showAddButton = true
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navToNewMeasurement() },
            shape = CircleShape,
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(vm.measurementList) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Icon(
                            painter = when (it.meterType) {
                                MeasurementTypes.WATER -> painterResource(id = R.drawable.water)
                                MeasurementTypes.LIGHT -> painterResource(id = R.drawable.lamp)
                                MeasurementTypes.GAS -> painterResource(id = R.drawable.gas_cylinder)
                            },
                            contentDescription = "",
//                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = when (it.meterType) {
                                MeasurementTypes.WATER -> stringResource(id = R.string.water).uppercase()
                                MeasurementTypes.LIGHT -> stringResource(id = R.string.light).uppercase()
                                MeasurementTypes.GAS -> stringResource(id = R.string.gas).uppercase()
                            }
                        )
                        Text(DecimalFormat.getInstance(Locale.getDefault()).format(it.value))
                        Text(dtf.format(it.date))
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}


