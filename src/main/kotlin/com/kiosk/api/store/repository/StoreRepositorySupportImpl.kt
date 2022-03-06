package com.kiosk.api.store.repository

import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.order.domain.entity.QOrder
import com.kiosk.api.order.domain.enums.OrderStatus
import com.kiosk.api.store.domain.entity.QStore
import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.model.StoreRequestDTO
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class StoreRepositorySupportImpl : StoreRepositorySupport {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private lateinit var queryFactory: JPAQueryFactory

    override fun findOrderPeriodbyStoreId(period: StoreRequestDTO.SearchRevenueByPeriod): Store? {
        queryFactory = JPAQueryFactory(entityManager)
        val qStore: QStore = QStore.store
        val qOrder: QOrder = qStore.order

        return queryFactory.select(qStore)
            .from(qStore).innerJoin(qOrder)
            .fetchJoin()
            .where(qStore.id.eq(period.id), qOrder.status.eq(OrderStatus.COMPLETE), qOrder.orderDate.between(period.startDate, period.endDate))
            .fetchOne()
    }

    override fun findOrdersByStoreIdAndCustomerId(orders: StoreRequestDTO.SearchOrdersOfAnCustomerInTheStore): List<Order> {
        queryFactory = JPAQueryFactory(entityManager)
        val qStore: QStore = QStore.store
        val qOrder: QOrder = qStore.order

        return queryFactory.selectDistinct(qOrder)
            .from(qStore).innerJoin(qOrder)
            .fetchJoin()
            .where(qStore.id.eq(orders.storeId), qOrder.id.eq(orders.customerId))
            .fetch()
    }
}