package com.miho.spring5mvcrest.bootstrap

import com.miho.spring5mvcrest.domain.Category
import com.miho.spring5mvcrest.repository.CategoryRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class Bootstrap(private val categoryRepository: CategoryRepository) : InitializingBean {

    override fun afterPropertiesSet() {
        val categories = listOf(Category("Fruits"), Category("Dried"), Category("Fresh"),
                Category("Exotic"), Category("Nuts"))

        categoryRepository.saveAll(categories)
    }
}