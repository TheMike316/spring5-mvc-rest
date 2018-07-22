package com.miho.api.v1.mapper

import com.miho.api.v1.model.CategoryDTO
import com.miho.spring5mvcrest.domain.Category
import org.mapstruct.Mapper

@Mapper
interface CategoryMapper {

    fun categoryToCategoryDTO(category: Category): CategoryDTO
}