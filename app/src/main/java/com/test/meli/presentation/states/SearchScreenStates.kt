package com.test.meli.presentation.states

import com.test.meli.data.network.HandledError

sealed class SearchScreenStates {
    data class LoadingState(val isVisible: Boolean) : SearchScreenStates()
    data class DataLoadedState(val data: Boolean) : SearchScreenStates()
    data class HandledErrorState(val error: HandledError) : SearchScreenStates()
}
