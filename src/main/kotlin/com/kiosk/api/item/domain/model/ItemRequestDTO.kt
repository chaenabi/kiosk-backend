package com.kiosk.api.item.domain.model

import com.kiosk.api.item.domain.entity.Item
import javax.validation.constraints.NotNull

class ItemRequestDTO {

    data class Save(
        @field:NotNull(message = "")
        val name: String,
        val price: Int = Int.MAX_VALUE,
        val quantity: Int = 0
    ) {
        fun toEntity(): Item {
            return Item(
                name = name,
                price = price,
                quantity = quantity
            )
        }
    }
}