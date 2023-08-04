package com.sajjadio.serverdrivenuiwithcompose.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val noteUiState by viewModel.noteUiState.collectAsState()
    val topAppBarUiState by viewModel.topAppBarUiState.collectAsState()
    val cardUiState by viewModel.cardUiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    HomeContent(
        noteUiState,
        topAppBarUiState,
        cardUiState,
        onRefreshUi = viewModel::onRefreshUi,
        isRefreshing = isRefreshing
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    noteUiState: NoteUiState,
    topAppBarUiState: TopAppBarUiState,
    cardUiState: CardUiState,
    onRefreshUi: () -> Unit,
    isRefreshing: Boolean
) {

    var topAppBarTitle by remember {
        mutableStateOf("")
    }
    var topAppBarBackgroundColor by remember {
        mutableStateOf(0L)
    }
    var topAppBarTitleColor by remember {
        mutableStateOf(0L)
    }


    // Card Ui
    var cardBackgroundColor by remember {
        mutableStateOf(0L)
    }
    var cardTitleColor by remember {
        mutableStateOf(0L)
    }
    var cardDescriptionColor by remember {
        mutableStateOf(0L)
    }
    var cardDateColor by remember {
        mutableStateOf(0L)
    }
    var cardShapeSize by remember {
        mutableStateOf(0)
    }
    var cardBorderWidth by remember {
        mutableStateOf(0)
    }
    var cardBorderColor by remember {
        mutableStateOf(0L)
    }

    if (topAppBarUiState.data != null) {
        topAppBarTitle = topAppBarUiState.data.title.toString()
        topAppBarBackgroundColor = topAppBarUiState.data.background
        topAppBarTitleColor = topAppBarUiState.data.title_color
    }

    if (cardUiState.data != null) {
        cardBackgroundColor = cardUiState.data.background
        cardTitleColor = cardUiState.data.title_color
        cardDescriptionColor = cardUiState.data.description_color
        cardDateColor = cardUiState.data.date_color
        cardShapeSize = cardUiState.data.shape_size
        cardBorderWidth = cardUiState.data.border_width
        cardBorderColor = cardUiState.data.border_color

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = topAppBarTitle) },
                colors = smallTopAppBarColors(
                    containerColor = Color(topAppBarBackgroundColor),
                    titleContentColor = Color(topAppBarTitleColor)
                )
            )
        },
    ) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing), onRefresh =  onRefreshUi) {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {

              if (noteUiState.error.isNotBlank()) {
                    item {
                        Text(text = noteUiState.error)
                    }
                } else {
                    items(noteUiState.data) { note ->
                        Card(
                            modifier = Modifier.padding(16.dp),
                            shape = RoundedCornerShape(cardShapeSize.dp),
                            colors = cardColors(containerColor = Color(cardBackgroundColor)),
                            border = BorderStroke(
                                width = cardBorderWidth.dp,
                                color = Color(cardBorderColor)
                            )
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = note.note_image),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = note.note_title.toString(), style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(cardTitleColor)
                                ),
                                modifier = Modifier.padding(16.dp)
                            )
                            Text(
                                text = note.note_description.toString(),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(cardDescriptionColor)
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = note.note_date_created.toString(), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(cardDateColor)
                                ),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
            }
        }
    }


}