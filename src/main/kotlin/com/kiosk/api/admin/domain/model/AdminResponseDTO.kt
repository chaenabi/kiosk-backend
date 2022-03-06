package com.kiosk.api.admin.domain.model

import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.store.domain.entity.Store

class AdminResponseDTO(admin: Admin? = null, store: Store? = null) {

    val id: Long
    val name: String
    val password: String
    val storeId: Long?

    init {
        this.id = admin?.id!!
        this.name = admin.name!!
        this.password = admin.password!!
        this.storeId = store?.id
    }

    data class SignInMapper(
        val id: Long,
        val name: String,
        val password: String,
        val storeId: Long?,
        val storeName: String
    )

    data class SignIn(
        private val adminResponseDTO: SignInMapper
    ) {
        val id: Long = adminResponseDTO.id
        val name: String = adminResponseDTO.name
        private val password: String = adminResponseDTO.password
        val storeId: Long? = adminResponseDTO.storeId
        val storeName: String = adminResponseDTO.storeName
    }
}