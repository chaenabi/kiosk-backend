package com.kiosk.api.customer.domain.model

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade

class CustomerRequestDTO {

    private var id: Long? = null
    private lateinit var contactNumber: String
    private var name: String? = null
    private lateinit var role: CustomerGrade
    private var isActive: Boolean = true

    data class Register(
        val contactNumber: String,
        val name: String,
        val role: CustomerGrade = CustomerGrade.NORMAL,
        val isActive: Boolean = true
    )

    data class Update(
        val contactNumber: String,
        val name: String,
    )

    data class Delete(
        val id: Long
    )

    private constructor()

    constructor(register: Register) {
        this.contactNumber = register.contactNumber
        this.name = register.name
        this.role = register.role
        this.isActive = register.isActive
    }

    constructor(update: Update) {
        this.contactNumber = update.contactNumber
        this.name = update.name
    }

    constructor(delete: Delete) {
        this.id = delete.id
    }

    fun toEntity(): Customer {
        return Customer(
            id = id,
            contactNumber = contactNumber,
            name = name,
            role = role,
            isActive = isActive
        )
    }

}