package com.kiosk.api.category.domain.entity

import com.kiosk.api.item.domain.entity.Item
import javax.persistence.*

@Entity
@IdClass(CategoryItemId::class)
class CategoryItem(
    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category,

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: Item
)

