package com.kiosk.api.item.domain.model

import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.ItemImage

class ItemImageDTO(
    val id: Long? = null,
    val name: String,
    val item: Item,
) {
    fun toEntity(): ItemImage {
        return ItemImage(
            name = name,
            item = item
        )
    }
}
