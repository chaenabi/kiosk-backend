package com.kiosk.api.admin.controller

import com.kiosk.api.admin.domain.entity.Admin
import com.kiosk.api.admin.domain.enums.AdminMessage
import com.kiosk.api.admin.service.AdminService
import com.kiosk.api.common.ResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class AdminController(
    private val adminService: AdminService
) {

    @GetMapping("/signIn")
    fun signIn(@RequestBody request: Any): ResponseDTO<Admin> {
        return ResponseDTO(adminService.signIn(request), AdminMessage.SUCCESS_SIGN_IN, HttpStatus.OK)
    }

}