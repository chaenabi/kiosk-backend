package com.kiosk.api.store.service

import com.kiosk.api.store.domain.entity.Store
import com.kiosk.api.store.domain.enums.StoreStatus
import com.kiosk.api.store.domain.model.StoreRequestDTO
import com.kiosk.api.store.domain.model.StoreResponseDTO
import com.kiosk.api.store.repository.StoreRepository
import com.kiosk.exception.common.BizException
import com.kiosk.exception.store.StoreCrudErrorCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, EmptyResultDataAccessException::class, SQLException::class])
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

    fun findOneStoreById(storeId: Long): StoreResponseDTO {
        return StoreResponseDTO(findOneEntityById(storeId))
    }

    fun findOneStoreByName(storeName: String): StoreResponseDTO {
        val findStore = storeRepository.findByName(storeName)
            .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
        return StoreResponseDTO(findStore)
    }

    fun findAllStores(): StoreResponseDTO.FindAll {
        return StoreResponseDTO.FindAll(storeRepository.findAll())
    }

    private fun findOneEntityById(storeId: Long): Store {
        return storeRepository.findById(storeId)
            .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
    }
}