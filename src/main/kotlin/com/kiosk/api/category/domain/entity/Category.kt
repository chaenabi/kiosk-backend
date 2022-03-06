package com.kiosk.api.category.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kiosk.api.category.domain.model.CategoryRequestDTO
import javax.persistence.*

@Entity
class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "category_id")
    var id: Long? = null,

    var parentId: Long? = null,
    @Column(unique = true)
    var name: String? = null,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("item")
    var items: MutableList<CategoryItem> = arrayListOf()
) {
    fun updateCategory(category: CategoryRequestDTO.Update) {
        this.parentId = category.parentId
        this.name = category.name!!
    }
}