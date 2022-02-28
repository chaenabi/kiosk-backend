package com.kiosk.api.item.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.customer.domain.enums.CustomerMessage
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.api.item.domain.model.ItemResponseDTO
import com.kiosk.api.item.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class ItemController(
    private val itemService: ItemService
) {

    @PostMapping("/item")
    fun saveItem(@RequestBody item: ItemRequestDTO.Save): ResponseDTO<ItemResponseDTO> {
        return ResponseDTO(itemService.save(item), CustomerMessage.SUCCESS_REGISTER, HttpStatus.OK)
    }

}