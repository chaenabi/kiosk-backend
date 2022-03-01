package com.kiosk.api.item.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.api.item.domain.model.ItemResponseDTO
import com.kiosk.api.item.domain.enums.ItemMessage
import com.kiosk.api.item.service.ItemService
import com.kiosk.exception.item.InvalidItemParameterException
import com.kiosk.exception.item.ItemCrudErrorCode
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class ItemController(
    private val itemService: ItemService
) {

    private lateinit var itemImages: MutableList<MultipartFile>

    @PostMapping("/item")
    fun saveItem(
        @Valid @RequestPart(value = "data")
        item: ItemRequestDTO.Save,
        @RequestPart(value = "attachImages", required = false)
        attachImages: MutableList<MultipartFile>? = null,
        result: BindingResult,
    ): ResponseDTO<ItemResponseDTO> {
        if (result.hasErrors()) throw InvalidItemParameterException(result, ItemCrudErrorCode.ITEM_CRUD_FAIL)
        itemImages = attachImages ?: Collections.emptyList()
        return ResponseDTO(itemService.save(item, itemImages), ItemMessage.SUCCESS_REGISTER, HttpStatus.OK)
    }

    @PostMapping("/item-update")
    fun updateItem(
        @Valid @RequestPart(value = "data")
        item: ItemRequestDTO.Update,
        @RequestPart(value = "attachImages", required = false)
        attachImages: MutableList<MultipartFile>? = null,
        result: BindingResult,
    ): ResponseDTO<ItemResponseDTO> {
        if (result.hasErrors()) throw InvalidItemParameterException(result, ItemCrudErrorCode.ITEM_CRUD_FAIL)
        itemImages = attachImages ?: Collections.emptyList()
        return ResponseDTO(itemService.update(item, itemImages), ItemMessage.SUCCESS_UPDATE, HttpStatus.OK)
    }

    @GetMapping("/item")
    fun findItemPage(@RequestParam page: Int, @RequestParam pageSize: Int): ResponseDTO<ItemResponseDTO.Paging> {
        return ResponseDTO(itemService.findItemPage(page, pageSize), ItemMessage.SUCCESS_FIND_AS_PAGE, HttpStatus.OK)
    }

    @DeleteMapping("/item/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseDTO<Unit> {
        itemService.delete(id)
        return ResponseDTO(ItemMessage.SUCCESS_DELETE_ONE, HttpStatus.OK)
    }

}