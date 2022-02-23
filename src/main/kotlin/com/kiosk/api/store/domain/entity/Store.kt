package com.kiosk.api.store.domain.entity

import com.kiosk.api.store.domain.enums.StoreStatus
import javax.persistence.*

@Entity
class Store(
    @Id @GeneratedValue @Column(name = "store_id")
    var id: Long?,

    var city: String,
    var street: String,
    var zipCode: String,

    @Enumerated(value = EnumType.STRING)
    var status: StoreStatus
)