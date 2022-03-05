package com.kiosk.api.admin.service

import com.kiosk.api.admin.domain.model.AdminRequestDTO
import com.kiosk.api.admin.domain.model.AdminResponseDTO
import com.kiosk.api.admin.repository.AdminRepository
import com.kiosk.exception.admin.AdminCrudErrorCode
import com.kiosk.exception.common.BizException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, EmptyResultDataAccessException::class, SQLException::class])
class AdminService(
    private val adminRepository: AdminRepository
) {

    fun signIn(request: AdminRequestDTO.SignIn): AdminResponseDTO {
        val findAdmin = adminRepository.findByName(request.name)
        findAdmin ?: throw BizException(AdminCrudErrorCode.ADMIN_NOT_FOUND)
        if (findAdmin.password == request.password) return AdminResponseDTO(findAdmin)
        else throw BizException(AdminCrudErrorCode.ADMIN_PASSWORD_IS_INVALID)
    }

    fun signUp(request: AdminRequestDTO.SignUp): AdminResponseDTO {
        return AdminResponseDTO(adminRepository.save(request.toEntity()))
    }

    fun updateAdmin(request: AdminRequestDTO.Update): AdminResponseDTO {
        val findAdmin = adminRepository.findById(request.id)
            .orElseThrow { BizException(AdminCrudErrorCode.ADMIN_NOT_FOUND) }
        return AdminResponseDTO(findAdmin.updateAdmin(request))
    }

    fun removeAdmin(adminId: Long) {
        val wantToRemove = adminRepository.findById(adminId)
            .orElseThrow { BizException(AdminCrudErrorCode.ADMIN_NOT_FOUND) }

        adminRepository.delete(wantToRemove)
    }
}