package com.miho.spring5mvcrest.api.v1.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun Any.toOkResponse() = ResponseEntity(this, HttpStatus.OK)

fun Any.toCreatedResponse() = ResponseEntity(this, HttpStatus.CREATED)