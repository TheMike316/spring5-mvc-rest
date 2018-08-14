package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.VendorMapper
import com.miho.spring5mvcrest.domain.Vendor
import com.miho.spring5mvcrest.repository.VendorRepository
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.*
import java.util.*

class VendorServiceImplTest {

    @Mock
    private lateinit var vendorRepository: VendorRepository

    @InjectMocks
    private lateinit var vendorService: VendorServiceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAllVendors() {
        //given
        val vendors = listOf(Vendor("Anne's Ants", 1), Vendor("Ferdinand and Son's", 2))
        Mockito.`when`(vendorRepository.findAll()).thenReturn(vendors)

        //when
        val readVendors = vendorService.getAllVendors()

        //then
        Assertions.assertThat(readVendors.vendors.size).isEqualTo(vendors.size)
    }

    @Test
    fun testGetVendorById() {
        //given
        val vendor = Vendor("Jeb and John", 2L)
        Mockito.`when`(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vendor))

        //when
        val readVendor = vendorService.getVendorById(2)

        //then
        Assertions.assertThat(readVendor.name).isEqualTo(vendor.name)
    }

    @Test
    fun testCreateNewVendor() {
        //given
        val vendor = Vendor("Ingrid's Italian Mushrooms", 3)
        Mockito.`when`(vendorRepository.save(vendor)).thenReturn(vendor)

        //when
        val savedVendor = vendorService.saveNewVendor(VendorMapper.convertVendorToDTO(vendor)!!)

        //then
        Assertions.assertThat(savedVendor.name).isEqualTo(vendor.name)
        Assertions.assertThat(savedVendor.vendor_url).isNotBlank()
    }

    @Test
    fun testUpdateVendor() {
        //given
        val vendor = Vendor("Johanns Fischfleischerei", 3)
        Mockito.`when`(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vendor))
        Mockito.`when`(vendorRepository.save(ArgumentMatchers.any(Vendor::class.java))).thenReturn(vendor)

        //when
        val updatedVendor = vendorService.updateVendor(10L, VendorMapper.convertVendorToDTO(vendor)!!)

        //then
        Assertions.assertThat(updatedVendor.name).isEqualTo(vendor.name)
        Assertions.assertThat(updatedVendor.vendor_url).isNotBlank()
    }

    @Test(expected = RuntimeException::class)
    fun testUpdateVendorSadPath() {
        //given
        val vendor = Vendor("Gustavs Gänsebraten", 3)
        Mockito.`when`(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty())

        //when
        vendorService.updateVendor(2L, VendorMapper.convertVendorToDTO(vendor)!!)

        //then exception is thrown
    }

    @Test
    fun testDeleteVendorById() {
        //given
        val vendor = Vendor("Fisch & Baums Baumfische", 1L)
        Mockito.`when`(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vendor))

        //when
        vendorService.deleteVendorById(vendor.id)

        //then
        Mockito.verify(vendorRepository, Mockito.times(1)).delete(ArgumentMatchers.any<Vendor>())
    }
}