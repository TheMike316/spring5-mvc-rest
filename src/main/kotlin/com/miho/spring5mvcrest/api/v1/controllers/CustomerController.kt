package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.CustomerDTO
import com.miho.spring5mvcrest.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(CustomerController.BASE_URL)
class CustomerController(private val customerService: CustomerService) {

    companion object {
        const val BASE_URL = "/api/v1/customers"
    }

    @GetMapping("/", "")
    fun getAllCustomers() = customerService.getAllCustomers()

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long) = customerService.getCustomerById(id)

    @PostMapping("/", "")
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewCustomer(@RequestBody customerDTO: CustomerDTO) = customerService.saveNewCustomer(customerDTO)

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody customerDTO: CustomerDTO) =
            customerService.updateCustomer(id, customerDTO)

    @PatchMapping("/{id}")
    fun patchCustomer(@PathVariable id: Long, @RequestBody customerDTO: CustomerDTO) =
            customerService.patchCustomer(id, customerDTO)

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) {
        customerService.deleteCustomerById(id)
    }
}