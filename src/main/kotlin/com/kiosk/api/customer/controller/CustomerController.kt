package com.kiosk.api.customer.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerMessage
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.service.CustomerService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class CustomerController(
    val customerService: CustomerService
) {
    @PostMapping("/customer")
    fun saveCustomer(@Valid @RequestBody customer: CustomerRequestDTO.Register): ResponseDTO<CustomerResponseDTO> {
        return ResponseDTO(customerService.register(customer), CustomerMessage.SUCCESS_REGISTER, HttpStatus.OK)
    }

    @GetMapping("/customer")
    fun findAllCustomer(): MutableList<Customer> {
        return customerService.findAll()
    }

    @GetMapping("/customer/{id}")
    fun findOneCustomer(@PathVariable("id") id: Long): ResponseDTO<CustomerResponseDTO> {
        return ResponseDTO(customerService.findOne(id), CustomerMessage.SUCCESS_FIND_ONE, HttpStatus.OK)
    }
}