package cl.jam.p2_examen.ui.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.jam.p2_examen.R
import cl.jam.p2_examen.helpers.getDate
import cl.jam.p2_examen.models.TipoServicio
import cl.jam.p2_examen.ui.vm.MeasurementViewModel
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate

import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeasurementUI(
    navToHome: () -> Unit = {},
    vm: MeasurementViewModel = viewModel(factory = MeasurementViewModel.Factory)
) {
    val context = LocalContext.current

    var medidor by remember { mutableStateOf("") }
    var strDate by remember { mutableStateOf(getDate()) }

    /***
     * Infomracion de date picker desde
     * https://www.youtube.com/watch?v=uAw87DdUnxg
     */
    val calendarState = rememberUseCaseState()
    val dialogState = rememberUseCaseState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        ), selection = CalendarSelection.Date() { d ->
            strDate = "${d.year}-${d.monthValue.toString().padStart(2, '0')}-${
                d.dayOfMonth.toString().padStart(2, '0')
            }"
            Log.v("SelecteDate", "$d")
        })


    Scaffold(topBar = {
        TopBar(
            title = "Agregar Medición",
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
                value = medidor,
                onValueChange = { medidor = it },
                label = { Text("Medidor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row() {
                OutlinedTextField(
                    value = strDate,
                    onValueChange = { },
                    label = { Text("Fecha") },
                    modifier = Modifier
//                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    readOnly = true
                )
                IconButton(
                    onClick = {
                        calendarState.show()
//                        datePickerDialog.show()
                    }, modifier = Modifier
//                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                }
            }
            RadioButtonExample()
            CoreSample1(dialogState, positiveClick = {
                Log.v("Dialogo", "positivo")
            }, negativeClick = {})
            Button(
                onClick = {
                    dialogState.show()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Registrar Medicion")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreSample1(
    dialogState: UseCaseState,
    positiveClick: () -> Unit,
    negativeClick: () -> Unit
) {
    CoreDialog(
        state = dialogState,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                "No", IconSource(Icons.Rounded.Notifications), ButtonStyle.FILLED
            ),
            positiveButton = SelectionButton(
                "Si", IconSource(Icons.Rounded.AccountCircle), ButtonStyle.ELEVATED
            ), onPositiveClick = {
                positiveClick()
            }, onNegativeClick = {
                negativeClick()
            }
        ),
        onPositiveValid = true,
        body = {
            Text(text = "Test")
        },
    )
}

@Composable
fun RadioButtonExample() {
    val radioOptions = TipoServicio.entries
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

    Column {
        Text("Medidor de:")
        radioOptions.forEach { option ->
            Row(
                Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option },
                )
                Text(
                    text = when (option) {
                        TipoServicio.AGUA -> stringResource(id = R.string.agua)
                        TipoServicio.LUZ -> stringResource(id = R.string.luz)
                        TipoServicio.GAS -> stringResource(id = R.string.gas)
                    }
                )
            }
        }
    }
}


