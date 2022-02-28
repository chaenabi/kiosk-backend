package com.kiosk.api.customer.domain.model

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import javax.validation.constraints.NotNull

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
        @field:NotNull(message = "ID 값이 반드시 전달되어야 합니다.")
        val id: Long?,
        val contactNumber: String?,
        val name: String?,
        val role: CustomerGrade?,
        val isActive: Boolean?
    )

    data class Delete(
        val id: Long
    )
}