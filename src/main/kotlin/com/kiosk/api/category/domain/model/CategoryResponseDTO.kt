package com.kiosk.api.category.domain.model

import com.kiosk.api.category.domain.entity.Category
import com.kiosk.api.item.domain.model.ItemResponseDTO

class CategoryResponseDTO {
    var id: Long? = null
    var parentId: Long? = null
    var name: String? = null
    var items: MutableList<ItemResponseDTO.ReProcessing> = mutableListOf()

    private constructor()

    constructor(category: Category) {
        this.id = category.id
        this.parentId = category.parentId
        this.name = category.name
        category.items.forEach {
            this.items.add(ItemResponseDTO.ReProcessing.mapping(it.item))
        }
    }

}