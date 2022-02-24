package com.kiosk.unit.repository

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.Grade
import com.kiosk.api.customer.repository.CustomerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@DataJpaTest
@DisplayName("소비자 레포지토리 테스트")
class CustomerRepositoryTest @Autowired constructor(
    val customerRepository: CustomerRepository
) {

    @Test
    fun `소비자 저장 테스트 - 성공`() {
        val customer: Customer = Customer(1L, LocalDateTime.now(), true, Grade.NORMAL)

        val savedCustomer = customerRepository.save(customer)

        assertThat(savedCustomer.id).isEqualTo(1)
        assertThat(savedCustomer.isActive).isEqualTo(true)
        assertThat(savedCustomer.role).isEqualTo(Grade.NORMAL)
    }

}