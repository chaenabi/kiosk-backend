package com.kiosk.api.item.repository

import com.kiosk.api.item.domain.entity.ItemImage
import org.springframework.stereotype.Repository

@Repository
interface ItemImageQueryDSLRepository {
    fun findByItemId(id: Long?): MutableIterable<ItemImage>
}