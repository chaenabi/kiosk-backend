package com.kiosk.api.category.repository

import com.kiosk.api.category.domain.entity.CategoryItem
import com.kiosk.api.category.domain.entity.CategoryItemId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryItemRepository : JpaRepository<CategoryItem, CategoryItemId>, CategoryRepositorySupport