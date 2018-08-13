package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.api.v1.model.VendorListDTO
import org.springframework.stereotype.Service

@Service
class VendorServiceImpl : VendorService {
    override fun getAllVendors(): VendorListDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getVendorById(id: Long): VendorDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveNewVendor(vendorDTO: VendorDTO): VendorDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateVendor(id: Long, vendorDTO: VendorDTO): VendorDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun patchVendor(id: Long, vendorDTO: VendorDTO): VendorDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteVendorById(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}