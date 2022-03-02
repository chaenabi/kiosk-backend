package com.kiosk.api.item.domain.model

import com.fasterxml.jackson.annotation.JsonIncludeProperties
import com.kiosk.api.item.domain.entity.Item
import org.springframework.beans.factory.annotation.Value
import javax.validation.constraints.NotNull

class ItemRequestDTO {

    class Save @JsonIncludeProperties("name", "detail", "price", "quantity") constructor(
        val name: String,
        val detail: String?,
        val price: Int?,
        val quantity: Int
    ) {
        fun toEntity(): Item {
            return Item(
                name = name,
                detail = detail ?: "",
                price = price ?: Int.MAX_VALUE,
                quantity = quantity,
            )
        }
    }

    class Update(
        @field:NotNull(message = "상품 번호가 반드시 전달되어야 합니다.")
        val id: Long?,
        val name: String?,
        val detail: String?,
        val price: Int?,
        val quantity: Int?,
    )
}