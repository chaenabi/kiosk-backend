package com.kiosk.api.order.domain.entity

import com.kiosk.api.customer.domain.entity.Customer
import com.kiosk.api.order.domain.enums.OrderStatus
import com.kiosk.api.store.domain.entity.Store
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_id")
    var id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16)
    var status: OrderStatus,

    @CreationTimestamp
    var orderDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    var customer: Customer? = null,

    @OneToOne(mappedBy = "order", fetch = LAZY, orphanRemoval = true)
    var store: Store? = null,

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    var orders: MutableList<OrderItem> = arrayListOf()
)