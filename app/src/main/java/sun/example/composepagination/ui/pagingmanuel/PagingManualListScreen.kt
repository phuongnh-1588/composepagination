package sun.example.composepagination.ui.pagingmanuel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import sun.example.utils.ListState

@Composable
fun PagingManualListScreen() {
    val viewModel = hiltViewModel<PagingManualListViewModel>()
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()


    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate &&
                    (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    val articles = viewModel.newsList

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE)
            viewModel.getNews()
    }

    LazyColumn(state = lazyColumnListState) {
        items(
            items = articles,
            key = { it.url },
        ) { article ->
            Text(
                modifier = Modifier
                    .height(75.dp),
                text = article.title,
            )

            Divider()
        }

        item(
            key = viewModel.listState,
        ) {
            when (viewModel.listState) {
                ListState.LOADING -> {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.White)
                    }
                }

                ListState.PAGINATING -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.White)
                    }
                }

                ListState.PAGINATION_EXHAUST -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(imageVector = Icons.Rounded.Face, contentDescription = "")

                        Text(text = "Nothing left.")

                        TextButton(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            elevation = ButtonDefaults.elevation(0.dp),
                            onClick = {
                                coroutineScope.launch {
                                    lazyColumnListState.animateScrollToItem(0)
                                }
                            },
                            content = {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.KeyboardArrowUp,
                                        contentDescription = ""
                                    )

                                    Text(text = "Back to Top")

                                    Icon(
                                        imageVector = Icons.Rounded.KeyboardArrowUp,
                                        contentDescription = ""
                                    )
                                }
                            }
                        )
                    }
                }

                else -> {}
            }
        }
    }
}