package com.kiosk.api.store.repository

import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.model.StoreRequestDTO
import org.springframework.stereotype.Repository

@Repository
interface StoreRepositorySupport {
    fun findOrderPeriodbyStoreId(period: StoreRequestDTO.SearchRevenueByPeriod): Store?
    fun findOrdersByStoreIdAndCustomerId(orders: StoreRequestDTO.SearchOrdersOfAnCustomerInTheStore): List<Order>
}