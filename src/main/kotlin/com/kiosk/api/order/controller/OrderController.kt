package com.kiosk.api.order.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.order.domain.enums.OrderMessage
import com.kiosk.api.order.domain.model.OrderRequestDTO
import com.kiosk.api.order.domain.model.OrderResponseDTO
import com.kiosk.api.order.service.OrderService
import com.kiosk.exception.order.InvalidOrderParameterException
import com.kiosk.exception.order.OrderCrudErrorCode
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class OrderController(
    val orderService: OrderService
) {

    @PostMapping("/order")
    fun createOrder(@Valid @RequestBody order: OrderRequestDTO.AddOrder, result: BindingResult): ResponseDTO<OrderResponseDTO> {
        if (result.hasErrors()) throw InvalidOrderParameterException(result, OrderCrudErrorCode.ORDER_CRUD_FAIL)
        return ResponseDTO(orderService.createOrder(order), OrderMessage.SUCCESS_ADD, HttpStatus.OK)
    }

    @DeleteMapping("/order/{id}/cancel")
    fun cancelOrder(@PathVariable("id") id: Long): ResponseDTO<Unit> {
        orderService.cancelOrder(id)
        return ResponseDTO(OrderMessage.SUCCESS_CANCEL, HttpStatus.OK)
    }

}