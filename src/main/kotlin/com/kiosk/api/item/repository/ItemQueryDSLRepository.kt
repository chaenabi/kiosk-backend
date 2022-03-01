package com.kiosk.api.item.repository

import com.kiosk.api.item.domain.entity.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface ItemQueryDSLRepository {
    fun findItemAsPage(pageable: Pageable): Page<Item>
}