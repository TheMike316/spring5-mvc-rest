package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.services.VendorService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Api("This API deals with vendors")
@RestController
@RequestMapping(VendorController.BASE_URL)
class VendorController(private val vendorService: VendorService) {

    companion object {
        const val BASE_URL = "/api/v1/vendors"
    }

    @ApiOperation("This will retrieve a list of all the vendors")
    @GetMapping("", "/")
    fun getAllVendors() = vendorService.getAllVendors()


    @ApiOperation("Get a vendor by its id")
    @GetMapping("/{id}")
    fun getVendorById(@PathVariable id: Long) = vendorService.getVendorById(id)

    @ApiOperation("Create a new vendor")
    @PostMapping("", "/")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveNewCustomer(@RequestBody vendor: VendorDTO) = vendorService.saveNewVendor(vendor)

    @ApiOperation("Update an existing vendor")
    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody vendor: VendorDTO) = vendorService.updateVendor(id, vendor)

    //Since vendors only have one property, the patch method might seem a bit redundant.
    //However, i think it should be implemented for completeness sake
    @ApiOperation("Update a vendor property")
    @PatchMapping("/{id}")
    fun patchCustomer(@PathVariable id: Long, @RequestBody vendor: VendorDTO) = vendorService.patchVendor(id, vendor)

    @ApiOperation("Delete a vendor by its id")
    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) {
        vendorService.deleteVendorById(id)
    }
}