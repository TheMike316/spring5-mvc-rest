package com.miho.spring5mvcrest.services

import com.miho.spring5mvcrest.api.v1.mapper.VendorMapper
import com.miho.spring5mvcrest.api.v1.model.VendorDTO
import com.miho.spring5mvcrest.api.v1.model.VendorListDTO
import com.miho.spring5mvcrest.exceptions.ResourceNotFound
import com.miho.spring5mvcrest.repository.VendorRepository
import org.springframework.stereotype.Service

@Service
class VendorServiceImpl(private val vendorRepository: VendorRepository) : VendorService {

    override fun getAllVendors() = vendorRepository.findAll()
            .map { VendorMapper.convertVendorToDTO(it) ?: throw IllegalStateException() }
            .let { VendorListDTO(it) }

    override fun getVendorById(id: Long) = vendorRepository.findById(id)
            .orElseThrow { ResourceNotFound() }
            .let { VendorMapper.convertVendorToDTO(it) ?: throw IllegalStateException() }

    override fun saveNewVendor(vendorDTO: VendorDTO) = vendorRepository.save(VendorMapper.convertDTOToVendor(vendorDTO)
            ?: throw IllegalArgumentException())
            .let { VendorMapper.convertVendorToDTO(it) ?: throw IllegalStateException() }

    override fun updateVendor(id: Long, vendorDTO: VendorDTO): VendorDTO {
        vendorRepository.findById(id).orElseThrow { ResourceNotFound() }

        val entity = VendorMapper.convertDTOToVendor(vendorDTO) ?: throw IllegalArgumentException()

        entity.id = id
        return vendorRepository.save(entity)
                .let { VendorMapper.convertVendorToDTO(it) ?: throw IllegalStateException() }
    }

    override fun patchVendor(id: Long, vendorDTO: VendorDTO): VendorDTO {
        val entity = vendorRepository.findById(id).orElseThrow { ResourceNotFound() }

        if (vendorDTO.name != null)
            entity.name = vendorDTO.name ?: throw IllegalStateException()

        return vendorRepository.save(entity)
                .let { VendorMapper.convertVendorToDTO(it) ?: throw IllegalStateException() }
    }

    override fun deleteVendorById(id: Long) = vendorRepository.findById(id)
            .orElseThrow { ResourceNotFound() }
            .let { vendorRepository.delete(it) }
}