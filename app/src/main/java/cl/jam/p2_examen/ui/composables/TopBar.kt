package cl.jam.p2_examen.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Preview()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    showAddButton: Boolean = true,
    showBackButton: Boolean = false,
    onButtonAddClicked: () -> Unit = {},
    onBackButtonClicked: () -> Unit = {}
) {
    val context = LocalContext.current
    val txtAdd = "Agregar Medición"
    val txtBack = "Volver"

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    onBackButtonClicked()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = txtBack
                    )
                }
            }
        },
        actions = {
            if (showAddButton) {
                IconButton(onClick = {
                    onButtonAddClicked()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = txtAdd
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}