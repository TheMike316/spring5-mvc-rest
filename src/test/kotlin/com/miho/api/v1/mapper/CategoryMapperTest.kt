package com.miho.api.v1.mapper

import com.miho.spring5mvcrest.domain.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mapstruct.factory.Mappers

class CategoryMapperTest {

    @Test
    fun testMapCategoryToDTO() {
        //given
        val category = Category("Freschly picked flowers", 3)

        //when
        val dto = Mappers.getMapper(CategoryMapper::class.java).categoryToCategoryDTO(category)

        //then
        assertThat(dto).isNotNull
        assertThat(dto.name).isEqualTo(category.name)
        assertThat(dto.id).isEqualTo(category.id)
    }
}