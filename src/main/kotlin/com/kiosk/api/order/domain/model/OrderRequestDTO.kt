package com.kiosk.api.order.domain.model

import java.time.LocalDateTime
import javax.validation.constraints.NotNull

class OrderRequestDTO {

    data class SearchOrdersByName(
        @field:NotNull(message = "회원 이름이 반드시 전달되어야 합니다.")
        val customerName: String,
        val startSearchDate: LocalDateTime? = null,
        val endSearchDate: LocalDateTime? = null
    )

}