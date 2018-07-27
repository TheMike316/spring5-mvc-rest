package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.api.v1.model.CustomerListDTO
import com.miho.spring5mvcrest.services.CustomerService
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as mockWhen

class CustomerControllerTest {

    @Mock
    private lateinit var customerService: CustomerService

    @InjectMocks
    private lateinit var customerController: CustomerController

    private lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build()
    }

    @Test
    fun getAllCustomers() {
        val customers = CustomerListDTO(listOf(CustomerDTO("Adam", "Average", 2), CustomerDTO("Eve", "Everlasting", 3)))
        mockWhen(customerService.getAllCustomers()).thenReturn(customers)

        mockMvc.perform(get("/api/v1/customers/"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.customers", hasSize<CustomerDTO>(2)))
    }

    @Test
    fun getCustomerById() {
        val customer = CustomerDTO("Geoff", "Gelöööööck", 4)
        mockWhen(customerService.getCustomerById(anyLong())).thenReturn(customer)

        mockMvc.perform(get("/api/v1/customers/4"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.firstName", equalTo(customer.firstName)))
                .andExpect(jsonPath("$.lastName", equalTo(customer.lastName)))
                .andExpect(jsonPath("$.id", equalTo(customer.id.toInt())))

    }
}