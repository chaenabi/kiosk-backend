package com.kiosk.api.customer.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class CustomerController {

    @GetMapping("/getCustomer")
    fun getCustomer(): String {
        return "get Customers"
    }
}