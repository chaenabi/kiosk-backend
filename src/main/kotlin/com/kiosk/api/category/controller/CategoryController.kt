package com.kiosk.api.category.controller

import com.kiosk.api.category.domain.enums.CategoryMessage
import com.kiosk.api.category.domain.model.CategoryRequestDTO
import com.kiosk.api.category.domain.model.CategoryResponseDTO
import com.kiosk.api.category.service.CategoryService
import com.kiosk.api.common.ResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping("/category")
    fun addNewCategory(@RequestBody request: CategoryRequestDTO.Add): ResponseDTO<Long> {
        return ResponseDTO(categoryService.addNewCategory(request), CategoryMessage.SUCCESS_ADD, HttpStatus.OK)
    }

    @PatchMapping("/category")
    fun updateCategory(@RequestBody request: CategoryRequestDTO.Update): ResponseDTO<Unit> {
        categoryService.update(request)
        return ResponseDTO(CategoryMessage.SUCCESS_UPDATE, HttpStatus.OK)
    }

    @GetMapping("/category/{id}")
    fun findOneCategory(@PathVariable("id") id: Long): ResponseDTO<CategoryResponseDTO> {
        return ResponseDTO(categoryService.findOne(id), CategoryMessage.SUCCESS_FIND_ONE, HttpStatus.OK)
    }

    @GetMapping("/category")
    fun findAllCategory(): ResponseDTO<List<CategoryResponseDTO>> {
        return ResponseDTO(categoryService.findAll(), CategoryMessage.SUCCESS_FIND_ONE, HttpStatus.OK)
    }

    @DeleteMapping("/category/{id}")
    fun deleteCategory(@PathVariable("id") id: Long): ResponseDTO<Unit> {
        categoryService.delete(id)
        return ResponseDTO(CategoryMessage.SUCCESS_DELETE_ONE, HttpStatus.OK)
    }

    @PostMapping("/category/item")
    fun addItemsInCategory(@RequestBody request: CategoryRequestDTO.AddItems): ResponseDTO<Unit> {
        categoryService.addItemsInCategory(request)
        return ResponseDTO(CategoryMessage.SUCCESS_ADD_ITEMS, HttpStatus.OK)
    }

    @DeleteMapping("/category/item")
    fun drawOffItemsInCategory(@RequestBody request: CategoryRequestDTO.DrawOffItems): ResponseDTO<Unit> {
        categoryService.drawOffItemsInCategory(request)
        return ResponseDTO(CategoryMessage.SUCCESS_DRAW_OFF_ITEMS, HttpStatus.OK)
    }

}