package com.kiosk.api.category.service

import com.kiosk.api.category.domain.entity.Category
import com.kiosk.api.category.domain.model.CategoryRequestDTO
import com.kiosk.api.category.domain.model.CategoryResponseDTO
import com.kiosk.api.category.repository.CategoryRepository
import com.kiosk.exception.category.CategoryCrudErrorCode
import com.kiosk.exception.common.BizException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, SQLException::class, EmptyResultDataAccessException::class])
class CategoryService(
    val categoryRepository: CategoryRepository
) {

    fun addNewCategory(request: CategoryRequestDTO.Add): Long {
        val addedCategory = categoryRepository.save(request.toEntity())
        return addedCategory.id!!
    }

    fun update(category: CategoryRequestDTO.Update) {
        val wantToUpdateCategory = findOneEntity(category.id)
        wantToUpdateCategory.updateCategory(category)
    }

    fun delete(id: Long) {
        categoryRepository.delete(findOneEntity(id))
    }

    fun addItemsInCategory(addItems: CategoryRequestDTO.AddItems) {
        TODO("Not yet implemented")
    }

    fun drawOffItemsInCategory(drawOffItems: CategoryRequestDTO.DrawOffItems) {
        TODO("Not yet implemented")
    }

    fun findOne(id: Long): CategoryResponseDTO {
        return CategoryResponseDTO(findOneEntity(id))
    }

    fun findAll(): List<CategoryResponseDTO> {
        val findAllCategory = categoryRepository.findAll()
        return findAllCategory.map { CategoryResponseDTO(it) }.toCollection(mutableListOf())
    }

    fun findItemsByCategoryName(categoryName: String): CategoryResponseDTO {
        TODO("Not yet implemented")
    }

    private fun findOneEntity(id: Long): Category = categoryRepository.findById(id)
        .orElseThrow { BizException(CategoryCrudErrorCode.CATEGORY_NOT_FOUND) }

}