package com.test.meli.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.meli.usecases.FetchRemoteProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchRemoteProductsUseCase: FetchRemoteProductsUseCase) :
    ViewModel() {

    fun init() {
        viewModelScope.launch {
            val result = fetchRemoteProductsUseCase.execute("Motorola%20G6#json")
            result.fold(functionLeft = {
                Log.d("Failed", "Failed")
            }, functionRight = {
                Log.d("Perfect", "Perfect")
            })
        }
    }
}