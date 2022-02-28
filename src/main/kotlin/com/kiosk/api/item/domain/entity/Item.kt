package com.kiosk.api.item.domain.entity

import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.item.domain.model.ItemRequestDTO
import javax.persistence.*

@Entity
class Item(
    @Id @GeneratedValue @Column(name = "item_id")
    var id: Long? = null,

    var name: String,
    var price: Int,
    var quantity: Int,
    var image: String? = null,

    @OneToMany(mappedBy = "item")
    var category: MutableList<CategoryItem> = arrayListOf(),

    @OneToMany(mappedBy = "item")
    var itemImage: MutableList<ItemImage> = arrayListOf()

) {
    fun updateItem(item: ItemRequestDTO.Update) {
        this.name = item.name
        this.price = this.price
        this.quantity = this.quantity
    }
}