package com.sajjadio.serverdrivenuiwithcompose.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.serverdrivenuiwithcompose.domain.Resource
import com.sajjadio.serverdrivenuiwithcompose.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()

    init {
        onRefreshUi()
    }

    fun onRefreshUi() {
        getNotes()
        getTopAppBarUi()
        getCardUi()
    }

    private fun getNotes() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            when (val resource = repository.getNote()) {
                is Resource.Success -> {
                    _isRefreshing.emit(false)
                    _noteUiState.emit(
                        NoteUiState(
                            data = resource.data.reversed()
                        )
                    )
                }

                is Resource.Error -> {
                    _isRefreshing.emit(false)
                    _noteUiState.emit(
                        NoteUiState(
                            error = resource.errorMessage
                        )
                    )
                }
            }
        }
    }

    private fun getTopAppBarUi() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            when (val resource = repository.getTopAppBarUi()) {
                is Resource.Success -> {
                    _topAppBarUiState.emit(TopAppBarUiState(data = resource.data))
                    _isRefreshing.emit(false)
                }

                is Resource.Error -> {
                    Log.d(this::class.simpleName, "getTopAppBarUi: ${resource.errorMessage}")
                    _isRefreshing.emit(false)
                    _topAppBarUiState.emit(TopAppBarUiState(data = null))
                }
            }
        }
    }

    private fun getCardUi() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            when (val resource = repository.getCardUi()) {
                is Resource.Success -> {
                    _cardUiState.emit(CardUiState(data = resource.data))
                    _isRefreshing.emit(false)
                }

                is Resource.Error -> {
                    Log.d(this::class.simpleName, "getCardUi: ${resource.errorMessage}")
                    _cardUiState.emit(CardUiState(data = null))
                    _isRefreshing.emit(false)
                }
            }
        }
    }


}