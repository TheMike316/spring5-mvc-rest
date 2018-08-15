package com.miho.spring5mvcrest.bootstrap

import com.miho.spring5mvcrest.domain.Category
import com.miho.spring5mvcrest.domain.Customer
import com.miho.spring5mvcrest.domain.Vendor
import com.miho.spring5mvcrest.repository.CategoryRepository
import com.miho.spring5mvcrest.repository.CustomerRepository
import com.miho.spring5mvcrest.repository.VendorRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class Bootstrap(private val categoryRepository: CategoryRepository,
                private val customerRepository: CustomerRepository,
                private val vendorRepository: VendorRepository) : InitializingBean {

    override fun afterPropertiesSet() {
        val categories = listOf(Category("Fruits"), Category("Dried"), Category("Fresh"),
                Category("Exotic"), Category("Nuts"))

        categoryRepository.saveAll(categories)

        val customers = listOf(Customer("First", "McFirstface"), Customer("Franz", "Stiegleitner"))

        customerRepository.saveAll(customers)

        val vendors = listOf(Vendor("Daphnes Delikatessen"), Vendor("Jürgen's Handmade Joghurt"), Vendor("Ball Mart"))

        vendorRepository.saveAll(vendors)
    }
}