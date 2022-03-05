package com.kiosk.api.order.repository

import com.kiosk.api.order.domain.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long>, OrderRepositorySupport