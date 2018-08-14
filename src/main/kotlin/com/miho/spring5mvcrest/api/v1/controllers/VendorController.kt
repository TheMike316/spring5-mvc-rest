package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.services.VendorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(VendorController.BASE_URL)
class VendorController(private val vendorService: VendorService) {

    companion object {
        const val BASE_URL = "/api/v1/vendors"
    }

    @GetMapping("", "/")
    fun getAllVendors() = vendorService.getAllVendors()

    @GetMapping("/{id}")
    fun getVendorById(@PathVariable id: Long) = vendorService.getVendorById(id)

    @PostMapping("", "/")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveNewCustomer(@RequestBody vendor: VendorDTO) = vendorService.saveNewVendor(vendor)

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody vendor: VendorDTO) = vendorService.updateVendor(id, vendor)

    //Since vendors only have one property, the patch method might seem a bit redundant.
    //However, i think it should be implemented for completeness sake
    @PatchMapping("/{id}")
    fun patchCustomer(@PathVariable id: Long, @RequestBody vendor: VendorDTO) = vendorService.patchVendor(id, vendor)

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) {
        vendorService.deleteVendorById(id)
    }
}