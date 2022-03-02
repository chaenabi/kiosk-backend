package com.kiosk.api.item.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.item.domain.model.ItemRequestDTO
import javax.persistence.*

@Entity
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long? = null,

    var name: String,
    var price: Int,
    var quantity: Int,

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("category")
    @JsonIgnore
    var category: MutableList<CategoryItem> = arrayListOf(),

    @OneToMany(mappedBy = "item", cascade = [CascadeType.REMOVE])
    @JsonIgnoreProperties("item")
    var images: MutableList<ItemImage> = arrayListOf()

) {
    fun updateItem(item: ItemRequestDTO.Update) {
        this.name = item.name
        this.price = this.price
        this.quantity = this.quantity
    }
}