package com.sajjadio.serverdrivenuiwithcompose.data.datasource.model

import java.util.Date

data class Note(
    val note_id: Int? = null,
    val note_title: String? = null,
    val note_description: String? = null,
    val note_image: String? = null,
    val note_date_created: Date? = null,
)