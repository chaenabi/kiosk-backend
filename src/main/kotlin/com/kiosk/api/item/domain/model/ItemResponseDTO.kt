package com.kiosk.api.item.domain.model

import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.item.domain.entity.Item

class ItemResponseDTO {
    var id: Long? = null

    lateinit var name: String
    var price: Int? = null
    var quantity: Int? = null
    var image: String? = null
    lateinit var category: List<CategoryItem>

    private constructor()

    constructor(item: Item) {
        this.id = item.id
        this.name = item.name
        this.price = item.price
        this.quantity = item.quantity
        this.image = item.image
        this.category = item.category
    }

}