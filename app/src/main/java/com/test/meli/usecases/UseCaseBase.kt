package com.test.meli.usecases

abstract class UseCaseBase<T> {
    abstract suspend fun execute(params: T?)
}