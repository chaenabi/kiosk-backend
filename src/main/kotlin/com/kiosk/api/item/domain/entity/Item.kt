package com.kiosk.api.item.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.exception.common.BizException
import com.kiosk.exception.item.ItemCrudErrorCode
import javax.persistence.*

@Entity
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long? = null,

    var name: String,
    var detail: String,
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
        this.name = item.itemName ?: this.name
        this.detail = item.detail ?: this.detail
        this.price = item.price ?: this.price
        this.quantity = item.quantity ?: this.quantity
    }

    fun addQuantity(quantity: Int) {
        this.quantity += quantity
    }

    fun substractQuantity(quantity: Int) {
        val restQuantity = this.quantity - quantity
        if (restQuantity < 0) {
            throw BizException(ItemCrudErrorCode.ITEM_NOT_INSUFFICIENT)
        }
        this.quantity = restQuantity
    }
}