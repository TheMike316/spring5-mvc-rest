package com.miho.spring5mvcrest.repository

import com.miho.spring5mvcrest.domain.Vendor
import org.springframework.data.repository.CrudRepository

interface VendorRepository : CrudRepository<Vendor, Long>