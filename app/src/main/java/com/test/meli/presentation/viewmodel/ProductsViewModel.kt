package com.test.meli.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.test.meli.usecases.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getAllProductsUseCase: GetAllProductsUseCase) :
    ViewModel() {}
