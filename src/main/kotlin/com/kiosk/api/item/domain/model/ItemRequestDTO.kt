package com.kiosk.api.item.domain.model

import com.fasterxml.jackson.annotation.JsonIncludeProperties
import com.kiosk.api.item.domain.entity.Item
import javax.validation.constraints.NotNull

class ItemRequestDTO {

    data class Save @JsonIncludeProperties("name", "price", "quantity") constructor(
        @field:NotNull(message = "상품 번호가 반드시 전달되어야 합니다.")
        val name: String,
        var price: Int?,
        var quantity: Int,
    ) {
        fun toEntity(): Item {
            return Item(
                name = name,
                price = price ?: Int.MAX_VALUE,
                quantity = quantity,
            )
        }
    }
}