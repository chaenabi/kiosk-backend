package com.kiosk.api.item.domain.entity

import com.kiosk.api.category.domain.entity.CategoryItem
import javax.persistence.*

@Entity
class Item(
    @Id @GeneratedValue @Column(name = "item_id")
    var id: Long? = null,

    var name: String,
    var price: Int,
    var quantity: Int,

    @OneToMany(mappedBy = "item")
    var category: MutableList<CategoryItem> = arrayListOf()
)