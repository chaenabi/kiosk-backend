package com.kiosk.api.category.domain.entity

import com.kiosk.api.category.domain.model.CategoryRequestDTO
import javax.persistence.*

@Entity
class Category(
    @Id @GeneratedValue @Column(name = "category_id")
    var id: Long? = null,

    var parentId: Long? = null,
    var name: String,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    var items: MutableList<CategoryItem> = arrayListOf()
) {
    fun updateCategory(category: CategoryRequestDTO.Update) {
        this.parentId = category.parentId
        this.name = category.name!!
    }
}