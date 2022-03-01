package com.kiosk.api.category.repository

import com.kiosk.api.category.domain.entity.CategoryItem
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepositorySupport {
    fun saveCategoryItem(categoryItem: CategoryItem)
    fun drawOffCategoryItem(categoryItem: CategoryItem)
}