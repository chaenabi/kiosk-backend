package com.kiosk.api.store.service

import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus
import com.kiosk.api.store.domain.model.StoreRequestDTO
import com.kiosk.api.store.domain.model.StoreResponseDTO
import com.kiosk.api.store.repository.StoreRepository
import com.kiosk.exception.common.BizException
import com.kiosk.exception.store.StoreCrudErrorCode
import org.springframework.stereotype.Service

@Service
class StoreService(
    val storeRepository: StoreRepository
) {

    fun registerStore(register: StoreRequestDTO.Register): StoreResponseDTO.Register {
        return StoreResponseDTO.Register(storeRepository.save(register.toEntity()))
    }

    fun updateStore(update: StoreRequestDTO.Update): StoreResponseDTO.Update {
        val store = findOneEntityById(update.id)
        return StoreResponseDTO.Update(store.updateStore(update))
    }

    fun removeStore(storeId: Long) {
        storeRepository.delete(findOneEntityById(storeId))
    }

    fun findOneStore(storeId: Long): StoreResponseDTO {
        return StoreResponseDTO(findOneEntityById(storeId))
    }

    fun findAllStores(): StoreResponseDTO.FindAll {
        return StoreResponseDTO.FindAll(storeRepository.findAll())
    }

    private fun findOneEntityById(storeId: Long): Store {
        return storeRepository.findById(storeId)
            .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
    }
}