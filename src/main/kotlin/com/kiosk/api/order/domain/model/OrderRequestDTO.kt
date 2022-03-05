package com.kiosk.api.order.domain.model

import java.time.LocalDateTime

class OrderRequestDTO {

    data class OrderSearch(
        val customerName: String,
        val customerId: Long,
        val startSearchDate: LocalDateTime,
        val endSearchDate: LocalDateTime
    )

}