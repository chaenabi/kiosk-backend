package com.kiosk.api.customer.domain.model

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import java.time.LocalDateTime

class CustomerResponseDTO {

    var id: Long? = null
    lateinit var registerDate: LocalDateTime
    var contactNumber: String? = null
    var name: String? = null
    lateinit var role: CustomerGrade

    private constructor()

    constructor(customer : Customer) {
        this.id = customer.id
        this.registerDate = customer.registerDate
        this.contactNumber = customer.contactNumber
        this.name = customer.name
        this.role = customer.role
    }

}