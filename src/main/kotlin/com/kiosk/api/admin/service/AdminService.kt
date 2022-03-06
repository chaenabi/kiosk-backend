package com.kiosk.api.admin.service

import com.kiosk.api.admin.domain.model.AdminRequestDTO
import com.kiosk.api.admin.domain.model.AdminResponseDTO
import com.kiosk.api.admin.repository.AdminRepository
import com.kiosk.api.store.repository.StoreRepository
import com.kiosk.exception.admin.AdminCrudErrorCode
import com.kiosk.exception.common.BizException
import com.kiosk.exception.customer.CustomerCrudErrorCode
import com.kiosk.exception.store.StoreCrudErrorCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, EmptyResultDataAccessException::class, SQLException::class])
class AdminService(
    private val adminRepository: AdminRepository,
    private val storeRepository: StoreRepository
) {

    @Transactional(readOnly = true)
    fun signIn(request: AdminRequestDTO.SignIn): AdminResponseDTO.SignIn {
        val findAdmin = adminRepository.findByName(request.name!!)
        findAdmin ?: throw BizException(AdminCrudErrorCode.ADMIN_NOT_FOUND)
        if (findAdmin.password != request.password) throw BizException(AdminCrudErrorCode.ADMIN_PASSWORD_IS_INVALID)
        return AdminResponseDTO.SignIn(findAdmin)
    }

    fun signUp(request: AdminRequestDTO.SignUp): AdminResponseDTO {
        if (adminRepository.findByName(request.name!!) != null) throw BizException(AdminCrudErrorCode.ADMIN_NAME_DUPLICATE)
        val store = storeRepository.findByName(request.storeName!!)
            .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
        return AdminResponseDTO(adminRepository.save(request.toEntity(store)), store)
    }

    fun updateAdmin(request: AdminRequestDTO.Update): AdminResponseDTO {
        val findAdmin = adminRepository.findById(request.adminId)
            .orElseThrow { BizException(AdminCrudErrorCode.ADMIN_NOT_FOUND) }
        if (request.storeId != null) {
            val findStore = storeRepository.findById(request.storeId)
                .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
            request.store = findStore
        }

        return AdminResponseDTO(findAdmin.updateAdmin(request))
    }

    fun removeAdmin(adminId: Long) {
        val wantToRemove = adminRepository.findById(adminId)
            .orElseThrow { BizException(AdminCrudErrorCode.ADMIN_NOT_FOUND) }

        adminRepository.delete(wantToRemove)
    }
}