package com.kiosk.api.admin.repository

import com.kiosk.api.admin.domain.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<Admin, Long> {
    fun findByName(name: String): Admin?
}