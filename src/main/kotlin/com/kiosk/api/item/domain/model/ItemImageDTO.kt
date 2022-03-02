package com.kiosk.api.item.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.ItemImage
import org.springframework.beans.factory.annotation.Value

class ItemImageDTO(
    val id: Long? = null,
    val name: String,
    val item: Item,
    @Value("\${src/main/resources/upload/image/}")
    @JsonIgnore
    val path: String?,
) {
    fun toEntity(): ItemImage {
        return ItemImage(
            name = name,
            path = path,
            item = item
        )
    }
}
