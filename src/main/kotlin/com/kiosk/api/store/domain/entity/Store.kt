package com.kiosk.api.store.domain.entity

import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.store.domain.enums.StoreStatus
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class Store(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "store_id")
    var id: Long? = null,

    var city: String? = null,
    var street: String? = null,
    @Column(length = 30)
    var zipCode: String? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    var status: StoreStatus = StoreStatus.OPEN,

    @OneToOne(mappedBy = "store", fetch = LAZY)
    var order: Order? = null,

    @OneToOne(mappedBy = "store", fetch = LAZY)
    var admin: Admin? = null
)