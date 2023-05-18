package com.test.meli.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.test.meli.presentation.events.SearchEvents
import com.test.meli.presentation.states.SearchScreenStates
import com.test.meli.usecases.FetchRemoteProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val fetchRemoteProductsUseCase: FetchRemoteProductsUseCase) :
    BaseViewModel<SearchEvents, SearchScreenStates>() {

    override fun manageEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.SearchEvent -> {
                searchProducts(event.query)
            }
        }
    }

    private fun searchProducts(query: String) {
        viewModelScope.launch {
            setState(SearchScreenStates.LoadingState(true))
            val result = fetchRemoteProductsUseCase.execute(query)
            result.fold(functionLeft = { error ->
                setState(SearchScreenStates.LoadingState(false))
                setState(SearchScreenStates.HandledErrorState(error))
            }, functionRight = { success ->
                setState(SearchScreenStates.LoadingState(false))
                setState(SearchScreenStates.DataLoadedState(success))
            })
        }
    }
}