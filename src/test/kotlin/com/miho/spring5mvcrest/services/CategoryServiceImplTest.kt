package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.domain.Category
import com.miho.spring5mvcrest.repository.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as mockWhen

class CategoryServiceImplTest {

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    private lateinit var categoryService: CategoryService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        categoryService = CategoryServiceImpl(categoryRepository)
    }

    @Test
    fun getAllCategories() {
        val categories = listOf(Category("Fish"), Category("Fruit"), Category("Flesh"))

        mockWhen(categoryRepository.findAll()).thenReturn(categories)

        val readCategories = categoryService.getAllCategories()

        assertThat(readCategories.categories.map { it.id }).containsOnly(*categories.map { it.id }.toTypedArray())
        assertThat(readCategories.categories.map { it.name }).containsOnly(*categories.map { it.name }.toTypedArray())
    }

    @Test
    fun getCategoryByName() {
        val category = Category("Tree")

        mockWhen(categoryRepository.findByName(anyString())).thenReturn(category)

        val readCategory = categoryService.getCategoryByName("Tree")

        assertThat(readCategory).isNotNull
        assertThat(readCategory.id).isEqualTo(category.id)
        assertThat(readCategory.name).isEqualTo(category.name)
    }


}
