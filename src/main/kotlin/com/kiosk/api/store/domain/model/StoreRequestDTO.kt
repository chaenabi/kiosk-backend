package com.kiosk.api.store.domain.model

import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus
import javax.validation.constraints.NotNull

class StoreRequestDTO {
    data class Register(
        val city: String? = null,
        val street: String? = null,
        val zipCode: String? = null,
        val status: StoreStatus = StoreStatus.AWAIT,
        @field:NotNull(message = "지점 주인 이름 정보는 필수입니다.")
        val owner: String
    ) {
        fun toEntity(): Store {
            return Store(
                city = city,
                street = street,
                zipCode = zipCode,
                status = status,
                owner = owner
            )
        }
    }

    class Update(
        @field:NotNull(message = "지점 번호가 반드시 전달되어야 합니다.")
        val id: Long,
        val city: String? = null,
        val street: String? = null,
        val zipCode: String? = null,
        val status: StoreStatus? = null,
        val owner: String? = null
    )
}