package com.kiosk.api.order.repository

import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.order.domain.model.OrderRequestDTO
import org.springframework.stereotype.Repository

@Repository
interface OrderRepositorySupport {
    fun findOrdersByCustomerName(orderSearch: CustomerRequestDTO.SearchOrdersByNameAndPeriod): List<Order>
}