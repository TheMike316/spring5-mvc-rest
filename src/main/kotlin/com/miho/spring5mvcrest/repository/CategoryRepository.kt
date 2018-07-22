package com.miho.spring5mvcrest.repository

import com.miho.spring5mvcrest.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>