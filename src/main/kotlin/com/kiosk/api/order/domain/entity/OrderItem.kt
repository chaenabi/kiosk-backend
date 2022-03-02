package com.kiosk.api.order.domain.entity

import com.kiosk.api.item.domain.entity.Item
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_item_id")
    var id: Long,

    var orderPrice: Int,
    var count: Int,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    var item: Item
)