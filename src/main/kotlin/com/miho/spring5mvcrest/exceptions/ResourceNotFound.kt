package com.miho.spring5mvcrest.exceptions

class ResourceNotFound(message: String? = "", cause: Throwable? = null, enableSuppression: Boolean = false, writableStackTrace: Boolean = false) :
        RuntimeException(message, cause, enableSuppression, writableStackTrace)
