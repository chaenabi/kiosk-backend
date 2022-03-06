package com.kiosk.api.store.domain.model

import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class StoreRequestDTO {
    data class Register(
        val city: String? = null,
        val street: String? = null,
        val zipCode: String? = null,
        val status: StoreStatus = StoreStatus.AWAIT,
        @field:NotBlank(message = "지점 주인 이름 정보는 필수입니다.")
        val owner: String,
        @field:NotBlank(message = "지점명 정보가 반드시 필요합니다.")
        val name: String
    ) {
        fun toEntity(): Store {
            return Store(
                city = city,
                street = street,
                zipCode = zipCode,
                status = status,
                owner = owner,
                name = name
            )
        }
    }

    class Update(
        @field:Positive(message = "지점 번호가 반드시 전달되어야 합니다.")
        val id: Long,
        @field:NotBlank(message = "지점 이름 정보가 반드시 필요합니다.")
        val name: String? = null,
        val city: String? = null,
        val street: String? = null,
        val zipCode: String? = null,
        val status: StoreStatus? = null,
        val owner: String? = null,
    )
}