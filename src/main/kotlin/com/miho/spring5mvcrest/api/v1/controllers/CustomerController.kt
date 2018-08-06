package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.services.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/", "")
    fun getAllCustomers() = customerService.getAllCustomers().toOkResponse()

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long) = customerService.getCustomerById(id).toOkResponse()

    @PostMapping("/", "")
    fun createNewCustomer(@RequestBody customerDTO: CustomerDTO) = customerService.saveNewCustomer(customerDTO).toCreatedResponse()

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody customerDTO: CustomerDTO) =
            customerService.updateCustomer(id, customerDTO).toOkResponse()
}