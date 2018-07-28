package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.services.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/", "")
    fun getAllCustomers() = customerService.getAllCustomers().toResponseEntity()

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long) = customerService.getCustomerById(id).toResponseEntity()
}