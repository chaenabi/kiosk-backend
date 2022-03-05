package com.kiosk.api.order.repository

import com.kiosk.api.order.domain.model.OrderRequestDTO
import org.springframework.stereotype.Repository

@Repository
interface OrderRepositorySupport {
    fun findOrdersByCustomerName(orderSearch: OrderRequestDTO.OrderSearch)
}