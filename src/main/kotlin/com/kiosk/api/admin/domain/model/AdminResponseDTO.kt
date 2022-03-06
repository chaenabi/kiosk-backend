package com.kiosk.api.admin.domain.model

import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.store.domain.entity.Store

class AdminResponseDTO(admin: Admin, store: Store? = null) {

    val id: Long
    val name: String
    val store: Store?

    init {
        this.id = admin.id!!
        this.name = admin.name!!
        this.store = store
    }

}