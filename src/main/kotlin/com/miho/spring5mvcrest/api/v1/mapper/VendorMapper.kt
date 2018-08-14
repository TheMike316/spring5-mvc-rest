package com.miho.spring5mvcrest.api.v1.mapper

import com.miho.spring5mvcrest.api.v1.controllers.VendorController
import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.domain.Vendor

object VendorMapper {

    fun convertVendorToDTO(source: Vendor?) =
            if (source == null)
                null
            else
                VendorDTO(source.name, "${VendorController.BASE_URL}/${source.id}")

    fun convertDTOToVendor(source: VendorDTO?) =
            if (source == null)
                null
            else
                Vendor(source.name ?: "")
}