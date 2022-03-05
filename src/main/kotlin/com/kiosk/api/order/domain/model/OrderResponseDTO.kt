package com.kiosk.api.order.domain.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.order.domain.entity.Order
import java.time.LocalDateTime

class OrderResponseDTO {

    var orderId: Long? = null
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

    class SearchOrdersByName {

        var orderId: Long? = null
        lateinit var orderDate: LocalDateTime
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var orderedStore: String? = null

        fun mapping(findOrders: List<Order>): SearchOrdersByName {
            for (order in findOrders) {
                orderId = order.id
                orderDate = order.orderDate
                orderedStore = "${order.store?.city} ${order.store?.street}"
            }

            return this
        }
    }


}