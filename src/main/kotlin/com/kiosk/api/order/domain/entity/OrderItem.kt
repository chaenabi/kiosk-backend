package com.kiosk.api.order.domain.entity

import com.kiosk.api.item.domain.entity.Item
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "order_item_id")
    var id: Long? = null,

    var orderPrice: Int = 0,
    var count: Int = 0,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    var item: Item? = null
) {
    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem()
            orderItem.item = item
            orderItem.orderPrice = orderPrice
            orderItem.count = count

            item.substractQuantity(count)
            return orderItem
        }
    }

    fun cancel() {
        item?.addQuantity(count)
    }

    fun getTotalPrice(): Int {
        return orderPrice * count
    }
}