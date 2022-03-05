package com.kiosk.api.order.repository

import com.kiosk.api.customer.domain.entity.QCustomer
import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.order.domain.entity.QOrder
import com.kiosk.api.order.domain.model.OrderRequestDTO
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.persistence.EntityManager

@Repository
class OrderRepositorySupportImpl : OrderRepositorySupport {

    private lateinit var entityManager: EntityManager
    private lateinit var queryFactory: JPAQueryFactory

    override fun findOrdersByCustomerName(orderSearch: OrderRequestDTO.SearchOrdersByName): List<Order> {
        queryFactory = JPAQueryFactory(entityManager)
        val qOrder: QOrder = QOrder.order
        val qCustomer: QCustomer = QCustomer.customer
        val booleanBuilder: BooleanBuilder

        return queryFactory.select(qOrder)
            .from(qOrder)
            .innerJoin(qCustomer)
            .fetchJoin()
            .where(qCustomer.name.eq(orderSearch.customerName), between(qOrder, orderSearch.startSearchDate, orderSearch.endSearchDate))
            .fetch()
    }

    private fun between(qOrder: QOrder, startSearchDate: LocalDateTime?, endSearchDate: LocalDateTime?): BooleanExpression? {
        if (startSearchDate == null) return qOrder.orderDate.between(LocalDateTime.MIN, endSearchDate)
        if (endSearchDate == null) return qOrder.orderDate.between(startSearchDate, LocalDateTime.now())
        return qOrder.orderDate.between(startSearchDate, endSearchDate)
    }
}