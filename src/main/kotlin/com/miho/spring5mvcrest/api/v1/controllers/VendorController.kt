package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.services.VendorService
import org.springframework.stereotype.Controller

@Controller
class VendorController(private val vendorService: VendorService) {

    companion object {
        const val BASE_URL = "/api/v1/vendors"
    }
}