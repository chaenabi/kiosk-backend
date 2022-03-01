package com.kiosk.api.category.domain.model

import com.kiosk.api.category.domain.entity.Category
import com.kiosk.api.item.domain.entity.Item

class CategoryResponseDTO {
    var id: Long? = null
    var parentId: Long? = null
    lateinit var name: String
    var items: MutableList<Item> = mutableListOf()

    private constructor()

    constructor(category: Category) {
        this.id = category.id
        this.parentId = category.parentId
        this.name = category.name
        category.items.forEach { this.items.add(it.item) }
    }

}