package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.CustomerMapper
import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.api.v1.model.CustomerListDTO
import com.miho.spring5mvcrest.exceptions.ResourceNotFound
import com.miho.spring5mvcrest.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service //TODO check for changes on update and patch
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun getAllCustomers() = customerRepository.findAll()
            .map { CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException() }
            .let { CustomerListDTO(it) }

    override fun getCustomerById(id: Long) = customerRepository.findById(id)
            .orElseThrow { ResourceNotFound("None of them fancy customers o' yours with them fancy id was found") }
            .let { CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException() }

    override fun saveNewCustomer(customer: CustomerDTO) = customerRepository.save(CustomerMapper.convertDTOToCustomer(customer)
            ?: throw IllegalArgumentException())
            .let { CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException() }


    override fun updateCustomer(id: Long, customer: CustomerDTO): CustomerDTO {
        customerRepository.findById(id).orElseThrow { ResourceNotFound("That dude doesn't exist") }

        val customerEntity = CustomerMapper.convertDTOToCustomer(customer) ?: throw IllegalArgumentException("....")
        customerEntity.id = id

        return customerRepository.save(customerEntity).let {
            CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException()
        }
    }

    override fun patchCustomer(id: Long, customer: CustomerDTO): CustomerDTO {
        val entity = customerRepository.findById(id).orElseThrow { ResourceNotFound("Git gud, dude") }

        if (customer.firstname != null)
            entity.firstName = customer.firstname ?: throw IllegalStateException("Dafuq")

        if (customer.lastname != null)
            entity.lastName = customer.lastname ?: throw IllegalStateException("Dafuq")

        return customerRepository.save(entity).let {
            CustomerMapper.convertCustomerToDTO(it) ?: throw IllegalStateException()
        }
    }

    override fun deleteCustomerById(id: Long) = customerRepository.findById(id)
            .orElseThrow { ResourceNotFound("Dude not found, man") }
            .let { customerRepository.delete(it) }
}
