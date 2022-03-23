package com.kiosk.api.item.controller

import com.kiosk.api.common.ResponseDTO
import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.api.item.domain.model.ItemResponseDTO
import com.kiosk.api.item.domain.enums.ItemMessage
import com.kiosk.api.item.service.ItemService
import com.kiosk.exception.item.InvalidItemParameterException
import com.kiosk.exception.item.ItemCrudErrorCode
import io.swagger.annotations.*
import org.apache.commons.io.IOUtils
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.Valid

@Api(tags = ["ItemController"], description = "상품 API")
@RestController
@RequestMapping("/v1")
class ItemController(
    private val itemService: ItemService
) {

    private lateinit var itemImages: MutableList<MultipartFile>

    @ApiOperation("상품 정보를 받아 데이터베이스에 해당 상품을 등록합니다. 상품을 등록할 때는 카테고리 정보가 필요하지 않습니다.")
    @PostMapping("/item")
    fun saveItem(
        @Valid @RequestPart(value = "data")
        @ApiParam(name = "data", value = "상품 정보", required = true)
        item: ItemRequestDTO.Save,
        @RequestPart(value = "attachImages", required = false)
        @ApiParam(name = "attachImages", value = "상품 이미지", defaultValue = "[]", type = "List<MultipartFile>", required = false)
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

    @ApiImplicitParams(
        ApiImplicitParam(name = "page", value = "찾으려는 페이지 번호", dataType = "Int", required = true, paramType = "query", example = "1"),
        ApiImplicitParam(name = "pageSize", value = "찾으려는 상품의 갯수", defaultValue = "10", dataType = "Int", required = false, paramType = "query", example = "10")
    )
    @GetMapping("/item")
    fun findItemPage(@RequestParam page: Int, @RequestParam(required = false) pageSize: Int?): ResponseDTO<ItemResponseDTO.Paging> {
        return ResponseDTO(itemService.findItemPage(page, pageSize ?: 10), ItemMessage.SUCCESS_FIND_AS_PAGE, HttpStatus.OK)
    }

    @DeleteMapping("/item/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseDTO<Unit> {
        itemService.delete(id)
        return ResponseDTO(ItemMessage.SUCCESS_DELETE_ONE, HttpStatus.OK)
    }


}