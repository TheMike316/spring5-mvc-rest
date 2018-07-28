package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.CustomerMapper
import com.miho.spring5mvcrest.domain.Customer
import com.miho.spring5mvcrest.repository.CustomerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*
import org.mockito.Mockito.`when` as mockWhen

class CustomerServiceImplTest {

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @InjectMocks
    private lateinit var customerService: CustomerServiceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAllCustomers() {
        //given
        val customers = listOf(Customer("Anne", "Baum", 1), Customer("Ferdinand", "Fußfessel", 2))
        mockWhen(customerRepository.findAll()).thenReturn(customers)

        //when
        val readCustomers = customerService.getAllCustomers()

        //then
        assertThat(readCustomers.customers.size).isEqualTo(customers.size)
        //assertThat(readCustomers.customers.map { it.id }).containsOnly(*customers.map { it.id }.toTypedArray())
    }

    @Test
    fun testGetCustomerById() {
        //given
        val customer = Customer("Jeb", "Jeppenheimer", 2L)
        mockWhen(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer))

        //when
        val readCustomer = customerService.getCustomerById(2)

        //then
        assertThat(readCustomer.firstName).isEqualTo(customer.firstName)
        assertThat(readCustomer.lastName).isEqualTo(customer.lastName)
        // assertThat(readCustomer.id).isEqualTo(customer.id)
    }

    @Test
    fun testCreateNewCustomer() {
        //given
        val customer = Customer("Ingrid", "Ingratitüde")
        mockWhen(customerRepository.save(customer)).thenReturn(customer)

        //when
        val savedCustomer = customerService.saveNewCustomer(CustomerMapper.convertCustomerToDTO(customer)!!)

        //then
        assertThat(savedCustomer.firstName).isEqualTo(customer.firstName)
        assertThat(savedCustomer.lastName).isEqualTo(customer.lastName)
        assertThat(savedCustomer.customerUrl).isNotBlank()
    }
}