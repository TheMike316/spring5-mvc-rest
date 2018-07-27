package com.miho.spring5mvcrest.repository

import com.miho.spring5mvcrest.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>