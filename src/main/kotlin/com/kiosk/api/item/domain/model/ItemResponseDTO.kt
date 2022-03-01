package com.kiosk.api.item.domain.model

import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.ItemImage
import org.springframework.data.domain.Page
import kotlin.math.ceil

class ItemResponseDTO {
    var id: Long? = null

    lateinit var name: String
    var price: Int? = null
    var quantity: Int? = null
    var images: MutableList<String> = mutableListOf()
    var category: MutableList<String> = mutableListOf()

    private constructor()

    constructor(item: Item, itemImages: MutableList<ItemImage>) {
        this.id = item.id
        this.name = item.name
        this.price = item.price
        this.quantity = item.quantity
        itemImages.forEach { it.name?.let { name -> images.add(name) } }
        //this.category = category
        item.category.forEach { this.category.add(it.category.name) }
    }

    class Paging(selectedPageNumber: Int, selectedItems: Page<Item>) {
        val selectedPageNumber: Int = ceil((selectedPageNumber + 1) / 10.0).toInt()
        val selectedItemsContent: MutableList<Item> = selectedItems.content
        var totalPages: Int = selectedItems.totalPages
    }

}