package com.sajjadio.serverdrivenuiwithcompose.presentation.home

import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.Note

data class NoteUiState(
    val error:String = "",
    val data:List<Note> = emptyList()
)
