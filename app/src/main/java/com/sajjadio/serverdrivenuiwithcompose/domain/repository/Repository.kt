package com.sajjadio.serverdrivenuiwithcompose.domain.repository

import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.CardUiData
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.Note
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.TopAppBarUiData
import com.sajjadio.serverdrivenuiwithcompose.domain.Resource

interface Repository {
    suspend fun getNote(): Resource<List<Note>>
    suspend fun getTopAppBarUi(): Resource<TopAppBarUiData?>
    suspend fun getCardUi(): Resource<CardUiData?>

}