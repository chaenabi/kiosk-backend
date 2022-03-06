package com.kiosk.api.admin.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.kiosk.api.admin.domain.model.AdminRequestDTO
import com.kiosk.api.store.domain.entity.Store
import javax.persistence.*

@Entity
@Table(name = "admin")
class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "admin_id")
    var id: Long? = null,

    @Column(unique = true)
    var name: String? = null,
    var password: String? = null,

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties("admin")
    var store: Store? = null
) {
    fun updateAdmin(update: AdminRequestDTO.Update): Admin {
        this.name = update.name ?: this.name
        this.password = update.password ?: this.password
        this.store = update.store ?: this.store
        return this
    }
}