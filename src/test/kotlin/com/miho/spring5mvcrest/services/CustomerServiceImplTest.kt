package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.CustomerMapper
import com.miho.spring5mvcrest.domain.Customer
import com.miho.spring5mvcrest.repository.CustomerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
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
        assertThat(readCustomer.firstname).isEqualTo(customer.firstName)
        assertThat(readCustomer.lastname).isEqualTo(customer.lastName)
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
        assertThat(savedCustomer.firstname).isEqualTo(customer.firstName)
        assertThat(savedCustomer.lastname).isEqualTo(customer.lastName)
        assertThat(savedCustomer.customer_url).isNotBlank()
    }

    @Test
    fun testUpdateCustomer() {
        //given
        val customer = Customer("Johann", "Fischfleischer")
        mockWhen(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer))
        mockWhen(customerRepository.save(any(Customer::class.java))).thenReturn(customer)

        //when
        val updatedCustomer = customerService.updateCustomer(10L, CustomerMapper.convertCustomerToDTO(customer)!!)

        //then
        assertThat(updatedCustomer.firstname).isEqualTo(customer.firstName)
        assertThat(updatedCustomer.lastname).isEqualTo(customer.lastName)
        assertThat(updatedCustomer.customer_url).isNotBlank()
    }

    @Test(expected = RuntimeException::class)
    fun testUpdateCustomerSadPath() {
        //given
        val customer = Customer("Gustav", "Gänsebraten")
        mockWhen(customerRepository.findById(anyLong())).thenReturn(Optional.empty())

        //when
        customerService.updateCustomer(2L, CustomerMapper.convertCustomerToDTO(customer)!!)

        //then exception is thrown
    }

    @Test
    fun testDeleteCustomerById() {
        //given
        val customer = Customer("Fisch", "Baum", 1L)
        mockWhen(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer))

        //when
        customerService.deleteCustomerById(customer.id)

        //then
        verify(customerRepository, times(1)).delete(any<Customer>())
    }
}