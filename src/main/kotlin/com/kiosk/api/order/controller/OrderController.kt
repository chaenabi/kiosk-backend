package com.kiosk.api.order.controller

import com.kiosk.api.order.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class OrderController(
    val orderService: OrderService
) {


    @PostMapping("/order")
    fun createOrder(@RequestBody order: Any) {

    }

    @PatchMapping("/order/cancel")
    fun cancelOrder() {

    }

}