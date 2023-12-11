package sun.example.composepagination.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sun.example.composepagination.ui.paginglist.PagingListScreen
import sun.example.composepagination.ui.pagingmanuel.PagingManualListScreen

@Composable
fun NavigationComposable(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .padding(paddingValues),
        navController = navController,
        startDestination = "paging"
    ) {
        composable("paging") {
            PagingListScreen()
        }

        composable("manuel_paging") {
            PagingManualListScreen()
        }
    }
}