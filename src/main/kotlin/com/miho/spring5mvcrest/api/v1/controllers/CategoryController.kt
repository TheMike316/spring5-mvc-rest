package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.services.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/", "")
    @ResponseStatus(HttpStatus.OK)
    fun getCategories() = categoryService.getAllCategories()

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    fun getCategoryByName(@PathVariable name: String) = categoryService.getCategoryByName(name)
}