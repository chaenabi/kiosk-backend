package com.kiosk.api.item.service

import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.api.item.domain.model.ItemResponseDTO
import com.kiosk.api.item.repository.ItemImageRepository
import com.kiosk.api.item.repository.ItemRepository
import com.kiosk.api.item.utils.ItemImageManager
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.sql.SQLException

@Service
@Transactional(rollbackFor = [RuntimeException::class, EmptyResultDataAccessException::class, SQLException::class])
class ItemService(
    val itemRepository: ItemRepository,
    val itemImageManager: ItemImageManager,
    val itemImageRepository: ItemImageRepository
) {

    fun save(item: ItemRequestDTO.Save, images: List<MultipartFile>): ItemResponseDTO {
        val savedItem = itemRepository.save(item.toEntity())
        val images = itemImageManager.saveImageToDisk(images, savedItem)
        itemImageRepository.saveAll(images)
        return ItemResponseDTO(savedItem)
    }

}