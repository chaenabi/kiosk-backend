package com.kiosk.api.order.repository

import com.kiosk.api.order.domain.model.OrderRequestDTO
import org.springframework.stereotype.Repository

@Repository
class OrderRepositorySupportImpl: OrderRepositorySupport {

    override fun findOrdersByCustomerName(orderSearch: OrderRequestDTO.OrderSearch) {
        TODO("Not yet implemented")
    }
}