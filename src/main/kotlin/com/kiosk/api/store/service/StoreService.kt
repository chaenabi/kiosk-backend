package com.kiosk.api.store.service

import com.kiosk.api.customer.service.CustomerService
import com.kiosk.api.store.domain.entity.Store
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
    val storeRepository: StoreRepository,
    val customerService: CustomerService
) {

    fun registerStore(register: StoreRequestDTO.Register): StoreResponseDTO.Register {
        return StoreResponseDTO.Register(storeRepository.save(register.toEntity()))
    }

    fun updateStore(update: StoreRequestDTO.Update): StoreResponseDTO.Update {
        val store = findOneEntityById(update.storeId)
        return StoreResponseDTO.Update(store.updateStore(update))
    }

    fun removeStore(storeId: Long) {
        val wantToRemoveStore = findOneEntityById(storeId)
        wantToRemoveStore.removeStore()
    }

    fun findOneStoreById(storeId: Long): StoreResponseDTO.FindOne {
        return StoreResponseDTO.FindOne(findOneEntityById(storeId))
    }

    fun findOneStoreByName(storeName: String): StoreResponseDTO.FindOne {
        val findStore = storeRepository.findByName(storeName)
            .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
        return StoreResponseDTO.FindOne(findStore)
    }

    // 한 지점의 기간별 매출 조회
    fun getAndStoreRevenueByPeriod(period: StoreRequestDTO.SearchRevenueByPeriod): StoreResponseDTO.FindRevenue {
        val findStore: Store? = storeRepository.findOrderPeriodbyStoreId(period)
        findStore ?: throw BizException(StoreCrudErrorCode.STORE_NOT_FOUND)
        val soldList = StoreResponseDTO.FindRevenue.mapping(findStore, period)
        return StoreResponseDTO.FindRevenue(findStore, soldList)
    }

    // 특정 고객이 한 지점에서 주문한 전체 내역 조회
    @Transactional(readOnly = true)
    fun getOrdersByStoreIdAndCustomerId(orders: StoreRequestDTO.SearchOrdersOfAnCustomerInTheStore): StoreResponseDTO.SearchOrdersOfAnCustomerInTheStore {
        findOneEntityById(orders.storeId!!)
        customerService.findOneEntity(orders.customerId!!)
        val allOrdersOfAnCustomerInTheStore = storeRepository.findOrdersByStoreIdAndCustomerId(orders)
        val mappingResult = StoreResponseDTO.SearchOrdersOfAnCustomerInTheStore.mapping(allOrdersOfAnCustomerInTheStore)
        return StoreResponseDTO.SearchOrdersOfAnCustomerInTheStore(mappingResult)
    }

    fun findAllStores(): StoreResponseDTO.FindAll {
        return StoreResponseDTO.FindAll(storeRepository.findAll())
    }

    private fun findOneEntityById(storeId: Long): Store {
        return storeRepository.findById(storeId)
            .orElseThrow { BizException(StoreCrudErrorCode.STORE_NOT_FOUND) }
    }
}