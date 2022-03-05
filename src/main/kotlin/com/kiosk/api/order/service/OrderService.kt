package com.kiosk.api.order.service

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.customer.repository.CustomerRepository
import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.repository.ItemRepository
import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.order.domain.entity.OrderItem
import com.kiosk.api.order.domain.model.OrderRequestDTO
import com.kiosk.api.order.domain.model.OrderResponseDTO
import com.kiosk.api.order.repository.OrderRepository
import com.kiosk.exception.common.BizException
import com.kiosk.exception.customer.CustomerCrudErrorCode
import com.kiosk.exception.item.ItemCrudErrorCode
import com.kiosk.exception.order.OrderCrudErrorCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, EmptyResultDataAccessException::class, SQLException::class])
class OrderService(
    private val customerRepository: CustomerRepository,
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository
) {
    fun order(customerId: Long, itemId: Long, count: Int): OrderResponseDTO {
        val customer: Customer = customerRepository.findById(customerId)
            .orElseThrow { BizException(CustomerCrudErrorCode.CUSTOMER_NOT_FOUND) }
        val item: Item = itemRepository.findById(itemId)
            .orElseThrow { BizException(ItemCrudErrorCode.ITEM_NOT_FOUND) }

        val orderItem: OrderItem = OrderItem.createOrderItem(item, item.price, count)
        val order: Order = Order.createOrder(customer, orderItem)
        orderRepository.save(order)

        return OrderResponseDTO(order, customer)
    }

    fun cancelOrder(orderId: Long) {
        val order: Order = orderRepository.findById(orderId)
            .orElseThrow { BizException(OrderCrudErrorCode.ORDER_NOT_FOUND) }

        order.cancel()
    }

    fun searchOrdersByCustomerName(orderSearch: OrderRequestDTO.SearchOrdersByName): OrderResponseDTO.SearchOrdersByName {
        customerRepository.findByName(orderSearch.customerName)
            .orElseThrow { BizException(CustomerCrudErrorCode.CUSTOMER_NOT_FOUND) }
        val findOrders = orderRepository.findOrdersByCustomerName(orderSearch)
        return OrderResponseDTO.SearchOrdersByName().mapping(findOrders)
    }


}