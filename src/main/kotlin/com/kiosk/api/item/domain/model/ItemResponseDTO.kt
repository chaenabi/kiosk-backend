package com.kiosk.api.item.domain.model

import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.ItemImage

class ItemResponseDTO {
    var id: Long? = null

    lateinit var name: String
    var price: Int? = null
    var quantity: Int? = null
    var images: MutableList<String> = mutableListOf()
    lateinit var category: List<CategoryItem>

    private constructor()

    constructor(item: Item, itemImages: MutableList<ItemImage>) {
        this.id = item.id
        this.name = item.name
        this.price = item.price
        this.quantity = item.quantity
        itemImages.forEach { it.name?.let { name -> images.add(name) } }
        this.category = item.category
    }

}