package com.sajjadio.serverdrivenuiwithcompose.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.serverdrivenuiwithcompose.domain.Resource
import com.sajjadio.serverdrivenuiwithcompose.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _noteUiState = MutableStateFlow(NoteUiState())
    val noteUiState: StateFlow<NoteUiState> = _noteUiState.asStateFlow()

    private val _topAppBarUiState = MutableStateFlow(TopAppBarUiState())
    val topAppBarUiState: StateFlow<TopAppBarUiState> = _topAppBarUiState.asStateFlow()

    private val _cardUiState = MutableStateFlow(CardUiState())
    val cardUiState: StateFlow<CardUiState> = _cardUiState.asStateFlow()

    init {
        getNotes()
        onRefreshUi()
    }

    fun onRefreshUi(){
        getTopAppBarUi()
        getCardUi()
    }

    private fun getNotes() {
        viewModelScope.launch {
            _noteUiState.emit(NoteUiState(isLoading = true))
            when (val resource = repository.getNote()) {
                is Resource.Success -> {
                    _noteUiState.emit(
                        NoteUiState(
                            isLoading = false,
                            data = resource.data.reversed()
                        )
                    )
                }

                is Resource.Error -> {
                    _noteUiState.emit(
                        NoteUiState(
                            isLoading = false,
                            error = resource.errorMessage
                        )
                    )
                }
            }
        }
    }

    private fun getTopAppBarUi() {
        viewModelScope.launch {
            when (val resource = repository.getTopAppBarUi()) {
                is Resource.Success -> {
                    _topAppBarUiState.emit(TopAppBarUiState(data = resource.data) )
                }

                is Resource.Error -> {
                    Log.d(this::class.simpleName, "getTopAppBarUi: ${resource.errorMessage}")
                    _topAppBarUiState.emit(TopAppBarUiState(data = null))
                }
            }
        }
    }

    private fun getCardUi() {
        viewModelScope.launch {
            when (val resource = repository.getCardUi()) {
                is Resource.Success -> {
                    _cardUiState.emit(CardUiState(data = resource.data))
                }

                is Resource.Error -> {
                    Log.d(this::class.simpleName, "getCardUi: ${resource.errorMessage}")
                    _cardUiState.emit(CardUiState(data = null))
                }
            }
        }
    }


}