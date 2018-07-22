package com.miho.spring5mvcrest.services

import com.miho.api.v1.model.CategoryDTO

interface CategoryService {

    fun getAllCategories(): List<CategoryDTO>

    fun getCategoryByName(name: String): CategoryDTO
}