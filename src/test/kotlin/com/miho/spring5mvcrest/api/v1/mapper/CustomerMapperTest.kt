package com.miho.spring5mvcrest.api.v1.mapper

import com.miho.spring5mvcrest.domain.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CustomerMapperTest {


    @Test
    fun convertCustomerToDTO() {
        //given
        val customer = Customer("Testy", "McTestface", 100)

        //when
        val customerDTO = CustomerMapper.convertCustomerToDTO(customer)

        //then
        assertThat(customerDTO).isNotNull
        assertThat(customerDTO!!.firstName).isEqualTo(customer.firstName)
        assertThat(customerDTO.lastName).isEqualTo(customer.lastName)
        assertThat(customerDTO.id).isEqualTo(customer.id)
    }
}