package com.kiosk.api.store.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.store.domain.enums.StoreMessage
import com.kiosk.api.store.domain.model.StoreRequestDTO
import com.kiosk.api.store.domain.model.StoreResponseDTO
import com.kiosk.api.store.service.StoreService
import com.kiosk.exception.store.InvalidStoreParameterException
import com.kiosk.exception.store.StoreCrudErrorCode
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class StoreController(
    private val storeService: StoreService
) {

    @GetMapping("/store/all")
    fun findStoreAll(): ResponseDTO<StoreResponseDTO.FindAll> {
        return ResponseDTO(storeService.findAllStores(), StoreMessage.SUCCESS_FIND_ALL, HttpStatus.OK)
    }

    @GetMapping("/store/{id}")
    fun findStoreById(@PathVariable("id") id: Long): ResponseDTO<StoreResponseDTO.FindOne> {
        return ResponseDTO(storeService.findOneStoreById(id), StoreMessage.SUCCESS_FIND_ONE, HttpStatus.OK)
    }

    @GetMapping("/store")
    fun findStoreByName(@RequestParam(value = "name") name: String): ResponseDTO<StoreResponseDTO.FindOne> {
        return ResponseDTO(storeService.findOneStoreByName(name), StoreMessage.SUCCESS_FIND_ONE, HttpStatus.OK)
    }

    // 한 지점의 특정 기간내 매출 검색
    @GetMapping("/store/revenue")
    fun getAnStoreRevenueByPeriod(
        @Valid @RequestBody period: StoreRequestDTO.SearchRevenueByPeriod,
        result: BindingResult
    ): ResponseDTO<StoreResponseDTO.FindRevenue> {
        if (result.hasErrors()) throw InvalidStoreParameterException(result, StoreCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(
            storeService.getAndStoreRevenueByPeriod(period),
            StoreMessage.SUCCESS_FIND_REVENUE,
            HttpStatus.OK
        )
    }

    // 특정 고객의 한 지점에서 주문한 전체 내역 조회
    @GetMapping("/store/personal-order-history")
    fun getOrderHistoryListOfAnCustomer(
        @Valid @RequestBody orders: StoreRequestDTO.SearchOrdersOfAnCustomerInTheStore,
        result: BindingResult
    ): ResponseDTO<StoreResponseDTO.SearchOrdersOfAnCustomerInTheStore> {
        if (result.hasErrors()) throw InvalidStoreParameterException(result, StoreCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(
            storeService.getOrdersByStoreIdAndCustomerId(orders),
            StoreMessage.SUCCESS_FIND_REVENUE,
            HttpStatus.OK
        )
    }

    @PostMapping("/store")
    fun registerStore(
        @RequestBody requestRegister: StoreRequestDTO.Register,
        result: BindingResult
    ): ResponseDTO<StoreResponseDTO.Register> {
        if (result.hasErrors()) throw InvalidStoreParameterException(result, StoreCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(storeService.registerStore(requestRegister), StoreMessage.SUCCESS_REGISTER, HttpStatus.OK)
    }

    @PatchMapping("/store")
    fun updateStore(
        @RequestBody requestUpdate: StoreRequestDTO.Update,
        result: BindingResult
    ): ResponseDTO<StoreResponseDTO.Update> {
        if (result.hasErrors()) throw InvalidStoreParameterException(result, StoreCrudErrorCode.STORE_CRUD_FAIL)
        return ResponseDTO(storeService.updateStore(requestUpdate), StoreMessage.SUCCESS_UPDATE, HttpStatus.OK)
    }

    @DeleteMapping("/store/{id}")
    fun deleteStore(@PathVariable("id") id: Long): ResponseDTO<Unit> {
        storeService.removeStore(id)
        return ResponseDTO(StoreMessage.SUCCESS_DELETE_ONE, HttpStatus.OK)
    }
}