package com.kiosk.api.customer.domain.model

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

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
        @field:Positive(message = "회원 번호가 반드시 전달되어야 합니다.")
        val id: Long?,
        val contactNumber: String?,
        val name: String?,
        val role: CustomerGrade?,
        val isActive: Boolean?
    )

    data class Delete(
        val id: Long
    )

    data class SearchOrdersByNameAndPeriod(
        @field:NotBlank(message = "회원 이름이 반드시 전달되어야 합니다.")
        val customerName: String,
        val startSearchDate: LocalDateTime? = null,
        val endSearchDate: LocalDateTime? = null
    )

}