package com.test.meli.presentation.states

import com.test.meli.data.network.HandledError
import com.test.meli.repository.models.ResultsModel

sealed class SearchScreenStates {
    data class LoadingState(val isVisible: Boolean) : SearchScreenStates()
    data class DataLoadedState(val data: Boolean) : SearchScreenStates()
    data class HandledErrorState(val error: HandledError) : SearchScreenStates()

    data class HistoryProductsLoadedState(val data: List<ResultsModel>) : SearchScreenStates()
}
