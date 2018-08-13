package com.miho.spring5mvcrest.api.v1.controllers

import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.api.v1.model.VendorListDTO
import com.miho.spring5mvcrest.exceptions.ResourceNotFound
import com.miho.spring5mvcrest.services.VendorService
import com.miho.spring5mvcrest.utils.asJsonString
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as mockWhen

class VendorControllerTest {

    @Mock
    private lateinit var vendorService: VendorService

    @InjectMocks
    private lateinit var vendorController: VendorController

    private lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build()
    }

    @Test
    fun testGetAllVendors() {
        val vendors = VendorListDTO(listOf(VendorDTO("Fruity Factory"), VendorDTO("Elastic Eagles")))

        mockWhen(vendorService.getAllVendors()).thenReturn(vendors)

        mockMvc.perform(get(VendorController.BASE_URL))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.vendors", hasSize<VendorDTO>(2)))
    }

    @Test
    fun testCreateNewVendor() {
        val vendor = VendorDTO("Baum")
        val returnDTO = VendorDTO(vendor.name, "${VendorController.BASE_URL}/1")

        mockWhen(vendorService.saveNewVendor(vendor)).thenReturn(returnDTO)

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(vendor.asJsonString()))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.name", equalTo(returnDTO.name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(returnDTO.vendor_url)))
    }

    @Test
    fun testUpdateVendor() {
        val vendor = VendorDTO("Joe's Jambalaya")
        val returnDTO = VendorDTO(vendor.name, "${VendorController.BASE_URL}/3")

        mockWhen(vendorService.updateVendor(3, vendor)).thenReturn(returnDTO)

        mockMvc.perform(put("${VendorController.BASE_URL}/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(vendor.asJsonString()))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", equalTo(returnDTO.name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(returnDTO.vendor_url)))
    }

    @Test
    fun testPatchVendor() {
        val vendor = VendorDTO("Josephine's Jet Enginges")
        val returnDTO = VendorDTO("Josephine", "${VendorController.BASE_URL}/3")

        mockWhen(vendorService.patchVendor(3, vendor)).thenReturn(returnDTO)

        mockMvc.perform(patch("${VendorController.BASE_URL}/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(vendor.asJsonString()))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", equalTo(returnDTO.name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(returnDTO.vendor_url)))
    }

    @Test
    fun testDeleteVendor() {
        mockMvc.perform(delete("${VendorController.BASE_URL}/3"))
                .andExpect(status().isOk)

        verify(vendorService).deleteVendorById(anyLong())
    }

    @Test
    fun testNotFound() {
        mockWhen(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFound())

        mockMvc.perform(get("${VendorController.BASE_URL}/999"))
                .andExpect(status().isNotFound)
    }
}