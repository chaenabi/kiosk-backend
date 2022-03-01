package com.kiosk.api.customer.service

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.repository.CustomerRepository
import com.kiosk.exception.common.BizException
import com.kiosk.exception.customer.CustomerCrudErrorCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.sql.SQLException


@Service
@Transactional(rollbackFor = [RuntimeException::class, SQLException::class, EmptyResultDataAccessException::class])
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun register(customer: CustomerRequestDTO.Register): CustomerResponseDTO {
        val savedCustomer = customerRepository.save(customer.toEntity())
        return CustomerResponseDTO(savedCustomer)
    }

    fun update(customer: CustomerRequestDTO.Update): CustomerResponseDTO {
        val findCustomer = findOneEntity(customer.id!!)
        findCustomer.updateCustomer(customer)
        return CustomerResponseDTO(findCustomer)
    }

    fun deleteOne(id: Long) {
        customerRepository.delete(findOneEntity(id))
    }

    @Transactional(readOnly = true)
    fun findAll(): MutableList<Customer> = customerRepository.findAll()

    @Transactional(readOnly = true)
    fun findOne(id: Long): CustomerResponseDTO = CustomerResponseDTO(customerRepository.findById(id)
        .orElseThrow { BizException(CustomerCrudErrorCode.CUSTOMER_NOT_FOUND) })

    private fun findOneEntity(id: Long): Customer = customerRepository.findById(id)
        .orElseThrow { BizException(CustomerCrudErrorCode.CUSTOMER_NOT_FOUND) }
}