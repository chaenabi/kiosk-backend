package com.kiosk.unit.service

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.repository.CustomerRepository
import com.kiosk.api.customer.service.CustomerService
import com.kiosk.api.order.repository.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CustomerServiceTest {

    private val customerRepository: CustomerRepository = mockk()
    private val orderRepository: OrderRepository = mockk()
    private val customerService: CustomerService = CustomerService(customerRepository, orderRepository)

    @Test
    fun `회원 정보 저장 서비스 - 성공`() {
        // 준비
        val customerRegisterDTO = CustomerRequestDTO.Register(
            contactNumber = "010-1234-5678",
            name = "charlie",
        )

        every { customerRepository.save(any<Customer>()) }.returns(customerRegisterDTO.toEntity().also { it.id = 1L })

        // 실행
        val savedCustomer: CustomerResponseDTO = customerService.register(customerRegisterDTO)

        // 검증
        assertNotNull(savedCustomer)
        assertThat(savedCustomer.id).isEqualTo(1L)
        assertThat(savedCustomer.contactNumber).isEqualTo("010-1234-5678")
        assertThat(savedCustomer.role).isEqualTo(CustomerGrade.NORMAL)
        assertThat(savedCustomer.name).isEqualTo("charlie")
        assertThat(savedCustomer.registerDate).isNotNull

    }

}