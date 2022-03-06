package com.kiosk.api.category.service

import com.kiosk.api.category.domain.entity.Category
import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.category.domain.model.CategoryRequestDTO
import com.kiosk.api.category.domain.model.CategoryResponseDTO
import com.kiosk.api.category.repository.CategoryItemRepository
import com.kiosk.api.category.repository.CategoryRepository
import com.kiosk.api.item.repository.ItemRepository
import com.kiosk.api.item.service.ItemService
import com.kiosk.exception.category.CategoryCrudErrorCode
import com.kiosk.exception.common.BizException
import com.kiosk.exception.item.ItemCrudErrorCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, SQLException::class, EmptyResultDataAccessException::class])
class CategoryService(
    val categoryRepository: CategoryRepository,
    val categoryItemRepository: CategoryItemRepository,
    val itemRepository: ItemRepository,
    val itemService: ItemService
) {

    fun addNewCategory(request: CategoryRequestDTO.Add): Long {
        val addedCategory = categoryRepository.save(request.toEntity())
        return addedCategory.id!!
    }

    fun update(category: CategoryRequestDTO.Update) {
        val wantToUpdateCategory = findOneEntity(category.id!!)
        wantToUpdateCategory.updateCategory(category)
    }

    fun delete(id: Long) {
        categoryRepository.delete(findOneEntity(id))
    }

    fun addItemsInCategory(addItems: CategoryRequestDTO.AddItems) {
        if (!categoryRepository.existsById(addItems.categoryId!!)) throw BizException(CategoryCrudErrorCode.CATEGORY_NOT_FOUND)
        for (itemId in addItems.items!!) {
            if (!itemRepository.existsById(itemId)) throw BizException(ItemCrudErrorCode.ITEM_NOT_FOUND)
        }
        for (itemId in addItems.items) {
            val categoryItem = CategoryItem(findOneEntity(addItems.categoryId), itemService.findOneEntity(itemId))
            categoryItemRepository.saveCategoryItem(categoryItem)
        }
    }

    fun drawOffItemsInCategory(drawOffItems: CategoryRequestDTO.DrawOffItems) {
        if (!categoryRepository.existsById(drawOffItems.categoryId!!)) throw BizException(CategoryCrudErrorCode.CATEGORY_NOT_FOUND)
        for (itemId in drawOffItems.items!!) {
            if (!itemRepository.existsById(itemId)) throw BizException(ItemCrudErrorCode.ITEM_NOT_FOUND)
        }
        for (itemId in drawOffItems.items) {
            val categoryItem = CategoryItem(findOneEntity(drawOffItems.categoryId), itemService.findOneEntity(itemId))
            categoryItemRepository.drawOffCategoryItem(categoryItem)
        }
    }

    fun findOne(id: Long): CategoryResponseDTO {
        return CategoryResponseDTO(findOneEntity(id))
    }

    fun findAll(): List<CategoryResponseDTO> {
        val findAllCategory = categoryRepository.findAll()
        return findAllCategory.map { CategoryResponseDTO(it) }.toCollection(mutableListOf())
    }

    fun findItemsByCategoryName(categoryName: String): CategoryResponseDTO {
        val findCategory = categoryRepository.findItemsByName(categoryName)
            ?: throw BizException(CategoryCrudErrorCode.CATEGORY_NOT_FOUND)
        return CategoryResponseDTO(findCategory)
    }

    private fun findOneEntity(id: Long): Category = categoryRepository.findById(id)
        .orElseThrow { BizException(CategoryCrudErrorCode.CATEGORY_NOT_FOUND) }

}