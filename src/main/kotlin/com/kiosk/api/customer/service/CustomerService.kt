package com.kiosk.api.customer.service

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.repository.CustomerRepository
import com.kiosk.exception.common.BizException
import com.kiosk.exception.customer.CustomerCrudErrorCode
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.transaction.Transactional

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {
    fun register(customer: CustomerRequestDTO.Register): CustomerResponseDTO {
        val savedCustomer = customerRepository.save(customer.toEntity())
        return CustomerResponseDTO(savedCustomer)
    }

    @Transactional(rollbackOn = [RuntimeException::class])
    fun update(customer: CustomerRequestDTO.Update): CustomerResponseDTO {
        val findCustomer = customer.id!!.let { findOneEntity(it) }
        findCustomer.updateCustomer(customer)
        return CustomerResponseDTO(findCustomer)
    }

    fun findAll(): MutableList<Customer> = customerRepository.findAll()

    private fun findOneEntity(id: Long): Customer = customerRepository.findById(id)
        .orElseThrow { BizException(CustomerCrudErrorCode.CUSTOMER_NOT_FOUND) }

    fun findOne(id: Long): CustomerResponseDTO = CustomerResponseDTO(customerRepository.findById(id)
        .orElseThrow { BizException(CustomerCrudErrorCode.CUSTOMER_NOT_FOUND) })
}