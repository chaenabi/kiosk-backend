package com.kiosk.api.item.repository

import com.kiosk.api.item.domain.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Long>, ItemQueryDSLRepository