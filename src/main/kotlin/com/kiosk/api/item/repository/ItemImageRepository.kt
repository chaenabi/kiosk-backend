package com.kiosk.api.item.repository

import com.kiosk.api.item.domain.entity.ItemImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemImageRepository : JpaRepository<ItemImage, Long>