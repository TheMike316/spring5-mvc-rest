package com.miho.api.v1.mapper

import com.miho.api.v1.model.CategoryDTO
import com.miho.spring5mvcrest.domain.Category

object CategoryMapper {

    fun convertCategoryToDTO(category: Category?) = if (category == null) null else CategoryDTO(id = category.id, name = category.name)
}