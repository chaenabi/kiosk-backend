package com.kiosk.api.admin.service

import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.admin.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
   private val adminRepository: AdminRepository
) {

   fun signIn(request: Any): Admin {
      // TODO:
      return Admin()
   }
}