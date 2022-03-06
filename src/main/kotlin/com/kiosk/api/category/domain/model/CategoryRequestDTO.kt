package com.kiosk.api.category.domain.model

import com.kiosk.api.category.domain.entity.Category
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class CategoryRequestDTO {

    class Add(
        val parentId: Long? = null,
        @field:NotBlank(message = "반드시 카테고리명이 전달되어야 합니다.")
        val name: String?
    ) {
        fun toEntity(): Category {
            return Category(
                parentId = parentId,
                name = name
            )
        }
    }

    class Update(
        @field:Positive(message = "카테고리 번호가 반드시 전달되어야 합니다.")
        val id: Long,
        val parentId: Long?,
        val name: String?
    )

    class AddItems(
        @field:Positive(message = "카테고리 번호가 반드시 전달되어야 합니다.")
        val categoryId: Long,
        @field:NotEmpty(message = "아이템 번호가 하나 이상 전달되어야 합니다.")
        val items: List<Long>
    )

    class DrawOffItems(
        @field:Positive(message = "카테고리 번호가 반드시 전달되어야 합니다.")
        val categoryId: Long,
        @field:NotEmpty(message = "아이템 번호가 하나 이상 전달되어야 합니다.")
        val items: List<Long>
    )
}