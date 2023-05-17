package com.test.meli.data.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.test.meli.commons.Constants.Companion.BAD_REQUEST
import com.test.meli.commons.Constants.Companion.INTERNAL_SERVER
import com.test.meli.commons.Constants.Companion.NOT_FOUND
import com.test.meli.commons.Constants.Companion.UNAUTHORIZED
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorFactory @Inject constructor() {

    fun handledError(throwable: Throwable): HandledError {
        return when (throwable) {
            is IOException -> {
                HandledError.NetworkConnection(
                    "No hay conexión a internet"
                )
            }
            is HttpException -> extractHttpExceptions(throwable)
            else -> HandledError.UnExpected("Ups, ha ocurrido un error inesperado")
        }
    }

    private fun extractHttpExceptions(httpException: HttpException): HandledError {
        val body = httpException.response()?.errorBody()
        val gson = GsonBuilder().create()

        val responseBody = gson.fromJson(body.toString(), JsonObject::class.java)
        val httpError = gson.fromJson(responseBody, HttpError::class.java)

        return when (httpError.errorCode) {
            BAD_REQUEST ->
                HandledError.BadRequest(httpError.errorMessage)

            INTERNAL_SERVER ->
                HandledError.InternalServerError(httpError.errorMessage)

            UNAUTHORIZED ->
                HandledError.UnAuthorized(httpError.errorMessage)

            NOT_FOUND ->
                HandledError.ResourceNotFound(httpError.errorMessage)

            else ->
                HandledError.Unknown(httpError.errorMessage)
        }
    }
}