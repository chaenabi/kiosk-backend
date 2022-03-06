package com.kiosk.api.admin.controller

import com.kiosk.api.admin.domain.enums.AdminMessage
import com.kiosk.api.admin.domain.model.AdminRequestDTO
import com.kiosk.api.admin.domain.model.AdminResponseDTO
import com.kiosk.api.admin.service.AdminService
import com.kiosk.api.common.ResponseDTO
import com.kiosk.exception.admin.AdminCrudErrorCode
import com.kiosk.exception.admin.InvalidAdminParameterException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class AdminController(
    private val adminService: AdminService
) {

    @GetMapping("/admin/signIn")
    fun signIn(@Valid @RequestBody requestSignIn: AdminRequestDTO.SignIn, result: BindingResult): ResponseDTO<AdminResponseDTO.SignIn> {
        if (result.hasErrors()) throw InvalidAdminParameterException(result, AdminCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(adminService.signIn(requestSignIn), AdminMessage.SUCCESS_SIGN_IN, HttpStatus.OK)
    }

    // 문제
    @PostMapping("/admin/signUp")
    fun signUp(@Valid @RequestBody requestSignUp: AdminRequestDTO.SignUp, result: BindingResult): ResponseDTO<AdminResponseDTO> {
        if (result.hasErrors()) throw InvalidAdminParameterException(result, AdminCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(adminService.signUp(requestSignUp), AdminMessage.SUCCESS_SIGN_UP, HttpStatus.OK)
    }

    // 문제
    @PatchMapping("/admin/update")
    fun updateAdmin(@Valid @RequestBody requestUpdate: AdminRequestDTO.Update, result: BindingResult): ResponseDTO<AdminResponseDTO> {
        if (result.hasErrors()) throw InvalidAdminParameterException(result, AdminCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(adminService.updateAdmin(requestUpdate), AdminMessage.SUCCESS_UPDATE, HttpStatus.OK)
    }

    // 문제
    @DeleteMapping("/admin/remove/{id}")
    fun removeAdmin(@PathVariable("id") id: Long): ResponseDTO<Unit> {
        adminService.removeAdmin(id)
        return ResponseDTO(AdminMessage.SUCCESS_REMOVE, HttpStatus.OK)
    }
}