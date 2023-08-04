package com.sajjadio.serverdrivenuiwithcompose.data.datasource.remote

import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.CardUiData
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.Note
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.TopAppBarUiData

interface DocumentaryFireStore {
    suspend fun getNotes(): List<Note>
    suspend fun getTopAppBarUi(): TopAppBarUiData?
    suspend fun getCardUi(): CardUiData?
}