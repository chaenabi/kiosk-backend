package com.kiosk.api.store.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.order.domain.entity.Order
import com.kiosk.api.store.domain.enums.StoreStatus
import com.kiosk.api.store.domain.model.StoreRequestDTO
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class Store(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "store_id")
    var id: Long? = null,

    var owner: String? = null,

    var city: String? = null,
    var street: String? = null,
    @Column(length = 30)
    var zipCode: String? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    var status: StoreStatus = StoreStatus.AWAIT,

    @OneToOne(mappedBy = "store", fetch = LAZY)
    @JsonIgnoreProperties("store")
    var order: Order? = null,

    @OneToOne(mappedBy = "store", fetch = LAZY)
    @JsonIgnoreProperties("store")
    var admin: Admin? = null
) {
    fun updateStore(update: StoreRequestDTO.Update): Store {
        this.city = update.city ?: this.city
        this.street = update.street ?: this.street
        this.zipCode = update.zipCode ?: this.zipCode
        this.status = update.status ?: this.status
        this.owner = update.owner ?: this.owner
        return this
    }
}