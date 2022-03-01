package com.kiosk.api.category.repository

import com.kiosk.api.category.domain.entity.CategoryItem
import org.springframework.stereotype.Repository

@Repository
interface CategoryQueryDSLRepository {
    fun saveCategoryItem(categoryItem: CategoryItem)
    fun drawOffCategoryItem(categoryItem: CategoryItem)
}