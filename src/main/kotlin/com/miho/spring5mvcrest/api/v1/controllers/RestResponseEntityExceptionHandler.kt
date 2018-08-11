package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.exceptions.ResourceNotFound
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ResourceNotFound::class)
    fun handleNotFoundException(exception: Exception, request: WebRequest) =
            ResponseEntity("Resource Not Fount", HttpHeaders(), HttpStatus.NOT_FOUND)
}