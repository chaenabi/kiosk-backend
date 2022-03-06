package com.kiosk.api.admin.repository

import com.kiosk.api.admin.domain.model.AdminResponseDTO
import org.springframework.stereotype.Repository

@Repository
interface AdminRepositorySupport {
    fun findByName(name: String): AdminResponseDTO.SignInMapper?
}