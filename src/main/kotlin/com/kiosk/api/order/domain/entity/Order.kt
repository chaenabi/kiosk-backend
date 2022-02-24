package com.kiosk.api.order.domain.entity

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.order.domain.enums.OrderStatus
import com.kiosk.api.store.domain.entity.Store
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue @Column(name = "order_id")
    var id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16)
    var status: OrderStatus,
    var orderDate: LocalDateTime,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Customer,

    @OneToOne(fetch = LAZY, mappedBy = "order", orphanRemoval = true)
    @JoinColumn(name = "store_id")
    var store: Store,

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    var orders: MutableList<OrderItem>
)