package com.kiosk.api.item.domain.model

import com.fasterxml.jackson.annotation.JsonIncludeProperties
import com.kiosk.api.item.domain.entity.Item
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.beans.factory.annotation.Value
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@ApiModel(description = "상품 등록 및 상품 정보 수정 시 필요한 정보")
class ItemRequestDTO {

    @ApiModel(description = "상품 등록에 필요한 정보")
    class Save @JsonIncludeProperties("itemName", "detail", "price", "quantity") constructor(
        val itemName: String,
        val detail: String?,
        val price: Int?,
        val quantity: Int
    ) {
        fun toEntity(): Item {
            return Item(
                itemName = itemName,
                detail = detail ?: "",
                price = price ?: Int.MAX_VALUE,
                quantity = quantity,
            )
        }
    }

    class Update(
        @ApiModelProperty(notes = "상품 번호는 필수입니다.")
        @field:Positive(message = "상품 번호가 반드시 전달되어야 합니다.")
        val id: Long?,
        val itemName: String?,
        val detail: String?,
        val price: Int?,
        val quantity: Int?,
    )
}