package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.CategoryDTO
import com.miho.spring5mvcrest.services.CategoryService
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as mockWhen

class CategoryControllerTest {

    @Mock
    private lateinit var categoryService: CategoryService

    @InjectMocks
    private lateinit var categoryController: CategoryController

    private lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build()
    }

    @Test
    fun testListCategories() {
        val categories = listOf(CategoryDTO(1, "Cheese"), CategoryDTO(2, "Bacon"))

        mockWhen(categoryService.getAllCategories()).thenReturn(categories)

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.categories", hasSize<CategoryDTO>(2)))
    }

    @Test
    fun testGetByName() {
        val cat = CategoryDTO(1, "Jim")

        mockWhen(categoryService.getCategoryByName(ArgumentMatchers.anyString())).thenReturn(cat)

        mockMvc.perform(get("/api/v1/categories/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", equalTo("Jim")))
    }
}