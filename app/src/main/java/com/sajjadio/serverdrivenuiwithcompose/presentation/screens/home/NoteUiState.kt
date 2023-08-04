package com.sajjadio.serverdrivenuiwithcompose.presentation.screens.home

import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.Note

data class NoteUiState(
    val isLoading:Boolean = false,
    val error:String = "",
    val data:List<Note> = emptyList()
)
