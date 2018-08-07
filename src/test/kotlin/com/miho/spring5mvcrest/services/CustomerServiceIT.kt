package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.repository.CustomerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerServiceIT {

    @Autowired
    private lateinit var customerService: CustomerService

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    companion object {
        const val UPDATED_NAME = "Jesper"
    }

    @Test
    fun testPatch() {
        val customer = customerRepository.findAll().first()

        val patchedCustomer = customerService.patchCustomer(customer.id, CustomerDTO(firstname = UPDATED_NAME))

        assertThat(patchedCustomer.firstname).isEqualTo(UPDATED_NAME)
        assertThat(patchedCustomer.lastname).isEqualTo(customer.lastName)
        assertThat(patchedCustomer.customer_url).isNotBlank()
    }
}