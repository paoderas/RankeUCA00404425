package com.pdm0126.rankeuca00404425.screens.option_list

import com.pdm0126.rankeuca00404425.model.Option

sealed interface OptionState {

    object Loading : OptionState

    data class Success(val options: List<Option>) : OptionState

    data class Error(val message: String) : OptionState
}