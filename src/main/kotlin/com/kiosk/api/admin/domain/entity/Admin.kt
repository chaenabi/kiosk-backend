package com.kiosk.api.admin.domain.entity

import com.kiosk.api.store.domain.entity.Store
import javax.persistence.*

@Entity
class Admin(
    @Id @GeneratedValue @Column(name = "admin_id")
    var id: Long? = null,

    var name: String,
    var password: String,

    @OneToOne
    @JoinColumn(name = "admin")
    var store: Store
)