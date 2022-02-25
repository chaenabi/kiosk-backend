package com.kiosk.api.customer.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import java.time.LocalDateTime

class CustomerResponseDTO {

    var id: Long? = null

    lateinit var registerDate: String
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

    override fun toString(): String {
        return "CustomerResponseDTO(id=$id, registerDate=$registerDate, contactNumber=$contactNumber, name=$name, role=$role)"
    }


}