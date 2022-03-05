package com.kiosk.api.admin.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kiosk.api.store.domain.entity.Store
import javax.persistence.*

@Entity
@Table(name = "admin")
class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "admin_id")
    var id: Long? = null,

    var name: String? = null,
    var password: String? = null,

    @OneToOne
    @JoinColumn(name = "admin")
    @JsonIgnoreProperties("admin")
    var store: Store? = null
) {
    override fun toString(): String {
        return "Admin(id=$id, name='$name', password='$password', store=$store)"
    }
}