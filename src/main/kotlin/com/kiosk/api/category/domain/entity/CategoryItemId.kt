package com.kiosk.api.category.domain.entity

import com.kiosk.api.item.domain.entity.Item
import java.io.Serializable

data class CategoryItemId(
    var category: Category? = null,
    var item: Item? = null
): Serializable