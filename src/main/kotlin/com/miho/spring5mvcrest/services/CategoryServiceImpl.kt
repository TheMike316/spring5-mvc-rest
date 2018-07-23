package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.CategoryMapper
import com.miho.spring5mvcrest.api.v1.model.CategoryDTO
import com.miho.spring5mvcrest.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override fun getAllCategories(): List<CategoryDTO> = categoryRepository.findAll()
            .map { CategoryMapper.convertCategoryToDTO(it) ?: throw IllegalStateException() }

    override fun getCategoryByName(name: String): CategoryDTO = categoryRepository.findByName(name)
            .let { CategoryMapper.convertCategoryToDTO(it) }
            ?: throw RuntimeException("Category could not be found")
}