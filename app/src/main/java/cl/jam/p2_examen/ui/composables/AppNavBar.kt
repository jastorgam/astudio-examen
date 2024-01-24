package cl.jam.p2_examen.ui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavBar(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeUI(
                navToNewMeasurement = { navController.navigate("new_measurement") }
            )
        }
        composable("new_measurement") {
            MeasurementUI(
                navToHome = { navController.navigate("home") }
            )
        }
    }
}