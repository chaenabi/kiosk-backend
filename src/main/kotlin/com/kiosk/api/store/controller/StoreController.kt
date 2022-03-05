package com.kiosk.api.store.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class StoreController {

    @GetMapping("/store/all")
    fun findStoreAll() {

    }

    @GetMapping("/store/{id}")
    fun findStoreById(@PathVariable("id") id: Long) {

    }

    @GetMapping("store")
    fun findStoreByName(@RequestParam(value = "name") name: String) {

    }

    // 한 지점의 특정 기간내 매출 검색
    @GetMapping("/store/revenue")
    fun getAnStoreRevenueByPeriod(@RequestBody period: Any) {

    }


    @PostMapping("/store")
    fun registerStore(@RequestBody store: Any) {

    }

    @PatchMapping("/store/{id}")
    fun updateStore(@PathVariable("id") id: Long) {

    }

    @DeleteMapping("/store/{id}")
    fun deleteStore(@PathVariable("id") id: Long) {

    }
}