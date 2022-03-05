package com.kiosk.api.admin.domain.model

import com.kiosk.api.admin.domain.entity.Admin

class AdminResponseDTO(admin: Admin) {

    val id: Long
    val name: String

    init {
        this.id = admin.id!!
        this.name = admin.name!!
    }

}