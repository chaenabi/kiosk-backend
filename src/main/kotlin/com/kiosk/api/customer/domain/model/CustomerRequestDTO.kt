package com.kiosk.api.customer.domain.model

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade

class CustomerRequestDTO {

    data class Register(
        val contactNumber: String? = null,
        val name: String? = null,
        val role: CustomerGrade = CustomerGrade.NORMAL,
        val isActive: Boolean = true
    ) {
        fun toEntity(): Customer {
            return Customer(
                contactNumber = contactNumber,
                name = name,
                role = role,
                isActive = isActive
            )
        }
    }

    data class Update(
        val id: Long?,
        val contactNumber: String,
        val name: String,
        val role: CustomerGrade,
        val isActive: Boolean
    ) {
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

    data class Delete(
        val id: Long
    ) {
        fun toEntity(): Customer {
            return Customer(id = id)
        }
    }
}