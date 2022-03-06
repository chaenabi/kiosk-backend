package com.kiosk.api.order.repository

import com.kiosk.api.order.domain.entity.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository: JpaRepository<OrderItem, Long> {
    fun findByOrderId(id: Long): List<OrderItem>
}