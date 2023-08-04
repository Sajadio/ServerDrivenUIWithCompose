package com.sajjadio.serverdrivenuiwithcompose.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.CardUiData
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.Note
import com.sajjadio.serverdrivenuiwithcompose.data.datasource.model.TopAppBarUiData
import com.sajjadio.serverdrivenuiwithcompose.utils.CARD_UI_DATA
import com.sajjadio.serverdrivenuiwithcompose.utils.NOTES
import com.sajjadio.serverdrivenuiwithcompose.utils.TOP_APP_BAR_UI_DATA
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DocumentaryFireStoreImpl @Inject
constructor(private val fireStore: FirebaseFirestore) : DocumentaryFireStore {
    override suspend fun getNotes(): List<Note> {
        return fireStore
            .collection(NOTES)
            .get()
            .await()
            .toObjects(Note::class.java)
    }

    override suspend fun getTopAppBarUi(): TopAppBarUiData? {
        return fireStore
            .collection(TOP_APP_BAR_UI_DATA)
            .get()
            .await()
            .toObjects(TopAppBarUiData::class.java)
            .first()
    }

    override suspend fun getCardUi(): CardUiData? {
        return fireStore
            .collection(CARD_UI_DATA)
            .get()
            .await()
            .toObjects(CardUiData::class.java)
            .first()
    }
}