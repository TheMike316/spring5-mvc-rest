package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.services.CategoryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories/")
class CategoryController(private val categoryService: CategoryService) {
}