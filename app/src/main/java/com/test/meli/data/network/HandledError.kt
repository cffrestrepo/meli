package com.test.meli.data.network

sealed class HandledError {
    data class NetworkConnection(val message: String) : HandledError()
    data class BadRequest(val message: String) : HandledError()
    data class UnAuthorized(val message: String) : HandledError()
    data class InternalServerError(val message: String) : HandledError()
    data class ResourceNotFound(val message: String) : HandledError()
    data class UnExpected(val message: String) : HandledError()
    data class Unknown(val message: String) : HandledError()
}