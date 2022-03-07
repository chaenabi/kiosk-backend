package com.kiosk.api.order.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.order.domain.enums.OrderStatus
import com.kiosk.api.store.domain.entity.Store
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_id")
    var id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16)
    var status: OrderStatus = OrderStatus.COMPLETE,

    @CreationTimestamp
    var orderDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("orders")
    var customer: Customer? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties("orders", "store")
    var store: Store? = null,

    @OneToMany(mappedBy = "order")
    var orderItems: MutableList<OrderItem> = arrayListOf()
) {

    companion object {
        fun createOrder(customer: Customer, store: Store, vararg orderItems: OrderItem): Order {
            val order = Order()
            order.customer = customer
            for (orderItem in orderItems) {
                order.addOrderItem(orderItem)
            }
            order.store =  store
            return order
        }
    }

    private fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun cancel(orderId: Long) {
        this.status = OrderStatus.CANCEL
        for (orderItem in orderItems) {
            if (orderId == orderItem.order!!.id) {
                orderItem.cancel()
            }
        }
    }

    // 전체 주문 가격 조회
    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (orderItem in orderItems) {
            totalPrice += orderItem.getTotalPrice()
        }
        return totalPrice
    }
}