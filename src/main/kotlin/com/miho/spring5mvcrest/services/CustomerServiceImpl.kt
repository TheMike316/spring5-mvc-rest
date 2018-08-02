package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.CustomerMapper
import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.api.v1.model.CustomerListDTO
import com.miho.spring5mvcrest.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun getAllCustomers(): CustomerListDTO {
        return customerRepository.findAll()
                .map { CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException() }
                .let { CustomerListDTO(it) }
    }

    override fun getCustomerById(id: Long): CustomerDTO {
        return customerRepository.findById(id)
                .orElseThrow { RuntimeException("None of them fancy customers o' yours with them fancy id was found") }
                .let { CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException() }
    }

    override fun saveNewCustomer(customer: CustomerDTO): CustomerDTO {
        return customerRepository.save(CustomerMapper.convertDTOToCustomer(customer)
                ?: throw IllegalArgumentException())
                .let { CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException() }
    }
}
