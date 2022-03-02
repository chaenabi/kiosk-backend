package com.kiosk.api.admin.domain.entity

import com.kiosk.api.store.domain.entity.Store
import javax.persistence.*

@Entity
@Table(name = "administrator")
class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "admin_id")
    var id: Long? = null,

    var name: String,
    var password: String,

    @OneToOne
    @JoinColumn(name = "admin")
    var store: Store
) {
    override fun toString(): String {
        return "Admin(id=$id, name='$name', password='$password', store=$store)"
    }
}