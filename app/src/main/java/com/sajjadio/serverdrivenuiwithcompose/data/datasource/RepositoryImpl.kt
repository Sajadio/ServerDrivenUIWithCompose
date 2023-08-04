package com.sajjadio.serverdrivenuiwithcompose.data.datasource

import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.CardUiData
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.Note
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.TopAppBarUiData
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.remote.DocumentaryFireStore
import com.sajjadio.serverdrivenuiwithcompose.domain.Resource
import com.sajjadio.serverdrivenuiwithcompose.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val documentaryFireStore: DocumentaryFireStore,
) : Repository {
    override suspend fun getNote(): Resource<List<Note>> {
        return try {
            Resource.Success(documentaryFireStore.getNotes())
        } catch (e: Throwable) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getTopAppBarUi(): Resource<TopAppBarUiData?> {
        return try {
            Resource.Success(documentaryFireStore.getTopAppBarUi())
        } catch (e: Throwable) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getCardUi(): Resource<CardUiData?> {
        return try {
            Resource.Success(documentaryFireStore.getCardUi())
        } catch (e: Throwable) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}