package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.api.v1.model.VendorListDTO

interface VendorService {

    fun getAllVendors(): VendorListDTO

    fun getVendorById(id: Long): VendorDTO

    fun saveNewVendor(vendorDTO: VendorDTO): VendorDTO

    fun updateVendor(id: Long, vendorDTO: VendorDTO): VendorDTO

    fun patchVendor(id: Long, vendorDTO: VendorDTO): VendorDTO

    fun deleteVendorById(id: Long)
}