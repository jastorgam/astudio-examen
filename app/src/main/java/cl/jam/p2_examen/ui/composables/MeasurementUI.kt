package cl.jam.p2_examen.ui.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.jam.p2_examen.R
import cl.jam.p2_examen.helpers.getDate
import cl.jam.p2_examen.models.Measurement
import cl.jam.p2_examen.models.MeasurementTypes
import cl.jam.p2_examen.ui.vm.MeasurementViewModel
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import java.time.LocalDate
import java.time.LocalDateTime


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeasurementUI(
    navToHome: () -> Unit = {},
    vm: MeasurementViewModel = viewModel(factory = MeasurementViewModel.Factory)
) {
    var measurer by remember { mutableStateOf("") }
    var strDate by remember { mutableStateOf(getDate(LocalDate.now())) }
    var date by remember { mutableStateOf(LocalDateTime.now()) }
    var selectedOption by remember { mutableStateOf(MeasurementTypes.entries[0]) }

    /***
     * Infomracion de date picker desde
     * https://www.youtube.com/watch?v=uAw87DdUnxg
     */
    val calendarState = rememberUseCaseState()
    val dialogState = rememberUseCaseState()

    Scaffold(topBar = {
        TopBar(
            title = stringResource(id = R.string.addMeasurement),
            onBackButtonClicked = navToHome,
            showBackButton = true,
            showAddButton = false
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            OutlinedTextField(
                value = measurer,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        measurer = it
                    }
                },
                label = { Text(stringResource(id = R.string.measurer)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = measurer.isEmpty(),
            )
            Row() {
                OutlinedTextField(
                    value = strDate,
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.date)) },
                    modifier = Modifier.padding(bottom = 16.dp),
                    readOnly = true
                )
                IconButton(
                    onClick = {
                        calendarState.show()
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                }
            }

            Text(stringResource(id = R.string.measurementOf))
            MeasurementTypes.entries.forEach { option ->
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option },
                    )
                    Text(
                        text = when (option) {
                            MeasurementTypes.AGUA -> stringResource(id = R.string.water)
                            MeasurementTypes.LUZ -> stringResource(id = R.string.light)
                            MeasurementTypes.GAS -> stringResource(id = R.string.gas)
                        }
                    )
                }
            }

            Button(
                onClick = {

                    if (measurer.isEmpty()) {
                        dialogState.show()
                    }
                    vm.addMeasurement(
                        Measurement(
                            date = date,
                            value = measurer.toInt(),
                            meterType = selectedOption
                        )
                    )
                    navToHome()

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                enabled = measurer.isNotEmpty()

            ) {
                Text(stringResource(id = R.string.addMeasurement))
            }
        }
    }

    // Calendario
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        ), selection = CalendarSelection.Date() { d ->
            date = d.atStartOfDay()
            strDate = getDate(d)
            Log.v("SelecteDate", "$d")
        })

    // Dialogo para confirmar si esta la medicion ingresada
    CoreDialog(
        state = dialogState,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = null,
            positiveButton = SelectionButton(
                stringResource(id = R.string.close),
                icon = null,
                ButtonStyle.FILLED
            )
        ),
        onPositiveValid = true,
        body = {
            Text(stringResource(id = R.string.missingInformation))
        },
    )
}



