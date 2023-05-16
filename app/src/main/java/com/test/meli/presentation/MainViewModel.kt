package com.test.meli.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.meli.usecases.ProductInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val productInsertUseCase: ProductInsertUseCase) :
    ViewModel() {

    fun init() {
        viewModelScope.launch {
            productInsertUseCase.execute(null)
        }
    }
}