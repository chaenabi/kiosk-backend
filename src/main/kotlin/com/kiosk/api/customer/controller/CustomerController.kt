package com.kiosk.api.customer.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerMessage
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.service.CustomerService
import com.kiosk.exception.customer.CustomerCrudErrorCode
import com.kiosk.exception.customer.InvalidCustomerParameterException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping("/customer")
    fun saveCustomer(@RequestBody customer: CustomerRequestDTO.Register): ResponseDTO<CustomerResponseDTO> {
        return ResponseDTO(customerService.register(customer), CustomerMessage.SUCCESS_REGISTER, HttpStatus.OK)
    }

    @GetMapping("/customer")
    fun findAllCustomer(): MutableList<Customer> = customerService.findAll()

    @GetMapping("/customer/{id}")
    fun findOneCustomer(@PathVariable("id") id: Long): ResponseDTO<CustomerResponseDTO> {
        return ResponseDTO(customerService.findOne(id), CustomerMessage.SUCCESS_FIND_ONE, HttpStatus.OK)
    }

    @PatchMapping("/customer")
    fun updateCustomer(@Valid @RequestBody customer: CustomerRequestDTO.Update, result: BindingResult): ResponseDTO<CustomerResponseDTO> {
        if (result.hasErrors()) throw InvalidCustomerParameterException(result, CustomerCrudErrorCode.CUSTOMER_CRUD_FAIL)
        return ResponseDTO(customerService.update(customer), CustomerMessage.SUCCESS_UPDATE, HttpStatus.OK)
    }

    @DeleteMapping("/customer/{id}")
    fun deleteOneCustomer(@PathVariable("id") id: Long): ResponseDTO<Unit> {
        customerService.deleteOne(id)
        return ResponseDTO(CustomerMessage.SUCCESS_DELETE_ONE, HttpStatus.OK)
    }
}