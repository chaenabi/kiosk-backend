package com.kiosk.api.order.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.order.domain.entity.Order
import java.time.LocalDateTime

class OrderResponseDTO {

    var orderId: Long? = null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    lateinit var orderDate: LocalDateTime
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var customerName: String? = null
    var totalPrice: Int? = 0

    private constructor()

    constructor(order: Order, customer: Customer) {
        this.orderId = order.id
        this.orderDate = order.orderDate
        this.customerName = customer.name
        this.totalPrice = order.getTotalPrice()
    }

}