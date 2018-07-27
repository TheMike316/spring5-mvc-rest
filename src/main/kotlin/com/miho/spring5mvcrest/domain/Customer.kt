package com.miho.spring5mvcrest.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Customer(var firstName: String = "", var lastName: String = "",
                    @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)] var id: Long = -1)