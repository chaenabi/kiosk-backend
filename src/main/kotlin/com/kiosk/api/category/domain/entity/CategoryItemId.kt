package com.kiosk.api.category.domain.entity

import com.kiosk.api.item.domain.entity.Item
import java.io.Serializable

class CategoryItemId(
    var category: Category,
    var item: Item
): Serializable