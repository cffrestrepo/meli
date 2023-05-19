package com.test.meli.presentation.events

sealed class SearchEvents {
    data class SearchEvent(val query: String) : SearchEvents()
    object InitEvent : SearchEvents()
}
