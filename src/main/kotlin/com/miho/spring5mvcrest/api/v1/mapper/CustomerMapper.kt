package com.miho.spring5mvcrest.api.v1.mapper

import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.domain.Customer

object CustomerMapper {

    fun convertCustomerToDTO(customer: Customer?) =
            if (customer == null)
                null
            else
                CustomerDTO(firstName = customer.firstName, lastName = customer.lastName, id = customer.id)
}