package com.kiosk.api.customer.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.domain.enums.CustomerGrade
import com.kiosk.api.order.domain.entity.Order
import java.time.LocalDateTime

class CustomerResponseDTO {

    var id: Long? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    lateinit var registerDate: LocalDateTime
    var contactNumber: String? = null
    var name: String? = null
    lateinit var role: CustomerGrade

    private constructor()

    constructor(customer: Customer) {
        this.id = customer.id
        this.registerDate = customer.registerDate
        this.contactNumber = customer.contactNumber
        this.name = customer.name
        this.role = customer.role!!
    }

    class SearchOrdersByNameAndPeriod {

        var orderId: Long? = null
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        lateinit var orderDate: LocalDateTime
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var orderedStore: String? = null

        fun mapping(findOrders: List<Order>): SearchOrdersByNameAndPeriod {
            for (order in findOrders) {
                orderId = order.id
                orderDate = order.orderDate
                orderedStore = "${order.store?.city} ${order.store?.street}"
            }
            return this
        }
    }
}