package com.kiosk.api.category.domain.entity

import javax.persistence.*

@Entity
class Category(
    @Id @GeneratedValue @Column(name = "category_id")
    var id: Long? = null,

    var parentId: Long? = null,
    var name: String,

    @OneToMany(mappedBy = "category")
    var items: MutableList<CategoryItem>
)