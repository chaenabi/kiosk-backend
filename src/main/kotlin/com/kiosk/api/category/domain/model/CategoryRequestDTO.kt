package com.kiosk.api.category.domain.model

import com.kiosk.api.category.domain.entity.Category
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class CategoryRequestDTO {

    class Add(
        val parentId: Long? = null,
        @field:NotNull(message = "반드시 카테고리명이 전달되어야 합니다.")
        val name: String
    ) {
        fun toEntity(): Category {
            return Category(
                parentId = parentId,
                name = name
            )
        }
    }

    class Update(
        @field:NotNull(message = "카테고리 번호가 반드시 전달되어야 합니다.")
        val categoryId: Long,
        val parentId: Long?,
        val name: String?
    )

    class AddItems(
        @field:NotNull(message = "카테고리 번호가 반드시 전달되어야 합니다.")
        val categoryId: Long,
        @field:NotEmpty(message = "아이템 번호가 하나 이상 전달되어야 합니다.")
        val items: List<Int>
    )

    class DrawOffItems(
        @field:NotNull(message = "카테고리 번호가 반드시 전달되어야 합니다.")
        val categoryId: Long,
        @field:NotEmpty(message = "아이템 번호가 하나 이상 전달되어야 합니다.")
        val items: List<Int>
    )
}