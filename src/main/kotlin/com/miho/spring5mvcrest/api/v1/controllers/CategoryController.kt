package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.CategoryListDTO
import com.miho.spring5mvcrest.services.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/")
    fun getCategories() = CategoryListDTO(categoryService.getAllCategories()).toResponseEntity()

    @GetMapping("/{name}")
    fun getCategoryByName(@PathVariable name: String) = categoryService.getCategoryByName(name).toResponseEntity()
}

fun Any.toResponseEntity() = ResponseEntity(this, HttpStatus.OK)