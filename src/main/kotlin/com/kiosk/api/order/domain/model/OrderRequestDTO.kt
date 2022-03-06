package com.kiosk.api.order.domain.model

import javax.validation.constraints.Positive

class OrderRequestDTO {

    data class AddOrder(
        @field:Positive(message = "회원 번호가 반드시 전달되어야 합니다.")
        val customerId: Long,
        @field:Positive(message = "상품 번호가 반드시 전달되어야 합니다.")
        val itemId: Long,
        @field:Positive(message = "지점 번호가 반드시 전달되어야 합니다.")
        val storeId: Long,
        @field:Positive(message = "주문을 하려면 한 개 이상의 상품 갯수 요청이 필요합니다.")
        val count: Int
    )

}