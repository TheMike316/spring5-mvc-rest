package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.api.v1.model.CustomerListDTO

interface CustomerService {

    fun getAllCustomers(): CustomerListDTO

    fun getCustomerById(id: Long): CustomerDTO

    fun saveNewCustomer(customer: CustomerDTO): CustomerDTO

    fun updateCustomer(id: Long, customer: CustomerDTO): CustomerDTO
}