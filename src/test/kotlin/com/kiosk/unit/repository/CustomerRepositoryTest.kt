package com.kiosk.unit.repository

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import com.kiosk.api.customer.repository.CustomerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@DisplayName("소비자 레포지토리 테스트")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class CustomerRepositoryTest @Autowired constructor(
    val customerRepository: CustomerRepository
) {

    @Test
    fun `소비자 정보 저장 테스트`() {
        val customer = Customer(
            id = 1L,
            contactNumber = "010-1234-3456",
            role = CustomerGrade.NORMAL
        )

        val savedCustomer = customerRepository.save(customer)

        assertThat(savedCustomer.id).isEqualTo(1)
        assertThat(savedCustomer.isActive).isEqualTo(true)
        assertThat(savedCustomer.role).isEqualTo(CustomerGrade.NORMAL)
        assertThat(savedCustomer.name).isEqualTo("3456")

    }

}