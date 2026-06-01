package com.pdm0126.rankeuca00404425.screens.option_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.rankeuca00404425.data.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OptionViewModel: ViewModel() {
    private val repository = NetworkModule.optionRepository
    private val _uiState = MutableStateFlow<OptionState>(OptionState.Loading)

    val uiState: StateFlow<OptionState> = _uiState.asStateFlow()

    fun loadProducts() {
        _uiState.value = OptionState.Loading
        viewModelScope.launch {
            val result = repository.getOptions()

            result.onSuccess { domainOptions ->
                _uiState.value = OptionState.Success(options = domainOptions)
            }
            result.onFailure { exception ->
                _uiState.value = OptionState.Error(message = exception.message ?: "Error desconocido")
            }
        }
    }
}