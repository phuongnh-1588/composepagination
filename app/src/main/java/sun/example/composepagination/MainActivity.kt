package sun.example.composepagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sun.example.composepagination.ui.NavigationComposable
import sun.example.composepagination.ui.theme.ComposePaginationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePaginationTheme {
                navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(navController)
                }
            }
        }
    }
}


@Composable
fun MainScreen(
    navController: NavHostController,
) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("paging")
                    },
                    label = {
                        Text(text = "Paging 3")
                    },
                    icon = {
                        Icon(imageVector = Icons.Rounded.Home, contentDescription = "")
                    }
                )

                //TODO
                BottomNavigationItem(
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("manuel_paging")
                    },
                    label = {
                        Text(text = "Manuel Paging")
                    },
                    icon = {
                        Icon(imageVector = Icons.Rounded.List, contentDescription = "")
                    }
                )
            }
        },
        content = { paddingValues ->
            NavigationComposable(
                paddingValues = paddingValues,
                navController = navController
            )
        }
    )
}