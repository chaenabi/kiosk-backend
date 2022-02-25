package com.kiosk.api.customer.service

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {
    fun register(customer: CustomerRequestDTO.Register): CustomerResponseDTO {
        val savedCustomer = customerRepository.save(CustomerRequestDTO(customer).toEntity())
        return CustomerResponseDTO(savedCustomer)
    }

    fun findAll(): MutableList<Customer> = customerRepository.findAll()

    fun findOne(id: Long): CustomerResponseDTO {
        val findCustomer = customerRepository.findById(id)
                    .orElseThrow { RuntimeException("사용자를 찾지 못했습니다") }

        return CustomerResponseDTO(findCustomer)

    }
}