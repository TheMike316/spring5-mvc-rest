package com.miho.spring5mvcrest.api.v1.mapper

import com.miho.spring5mvcrest.api.v1.controllers.CustomerController
import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.domain.Customer

object CustomerMapper {

    fun convertCustomerToDTO(customer: Customer?) =
            if (customer == null)
                null
            else
                CustomerDTO(firstname = customer.firstName, lastname = customer.lastName, customer_url = createUrl(customer))

    private fun createUrl(customer: Customer) = "${CustomerController.BASE_URL}/${customer.id}"

    fun convertDTOToCustomer(dto: CustomerDTO?) =
            if (dto == null)
                null
            else
                Customer(dto.firstname ?: "", dto.lastname ?: "")
}