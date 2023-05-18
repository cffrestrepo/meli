package com.test.meli.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<in Event, State> : ViewModel() {

    protected val _screenState: MutableLiveData<State> = MutableLiveData()
    val screenState: LiveData<State>
        get() = _screenState

    fun postEvent(event: Event) {
        manageEvent(event)
    }

    protected fun setState(state: State) {
        state?.let {
            _screenState.value = it
        }
    }

    protected fun postState(state: State) {
        state?.let {
            _screenState.postValue(it)
        }
    }

    protected abstract fun manageEvent(event: Event)
}
