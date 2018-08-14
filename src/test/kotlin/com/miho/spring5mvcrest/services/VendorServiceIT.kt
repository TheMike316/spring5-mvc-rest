package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.repository.VendorRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class VendorServiceIT {

    @Autowired
    private lateinit var vendorService: VendorService

    @Autowired
    private lateinit var vendorRepository: VendorRepository

    companion object {
        const val UPDATED_NAME = "Jesper's Jesters"
    }

    //Since vendors only have one property, the patch method might seem a bit redundant.
    //However, i think it should be implemented for completeness sake
    @Test
    fun testPatch() {
        val vendor = vendorRepository.findAll().first()

        val patchedVendor = vendorService.patchVendor(vendor.id, VendorDTO(name = UPDATED_NAME))

        assertThat(patchedVendor.name).isEqualTo(UPDATED_NAME)
        assertThat(patchedVendor.vendor_url).isNotBlank()
    }
}