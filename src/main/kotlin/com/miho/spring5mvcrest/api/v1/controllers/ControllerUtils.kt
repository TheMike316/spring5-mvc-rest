package com.miho.spring5mvcrest.api.v1.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun Any.toResponseEntity() = ResponseEntity(this, HttpStatus.OK)