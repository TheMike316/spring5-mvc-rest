package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.model.CategoryDTO
import com.miho.spring5mvcrest.api.v1.model.CategoryListDTO

interface CategoryService {

    fun getAllCategories(): CategoryListDTO

    fun getCategoryByName(name: String): CategoryDTO
}