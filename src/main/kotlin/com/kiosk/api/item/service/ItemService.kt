package com.kiosk.api.item.service

import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.api.item.domain.model.ItemResponseDTO
import com.kiosk.api.item.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [RuntimeException::class])
class ItemService(
    val itemRepository: ItemRepository
) {

    fun save(item: ItemRequestDTO.Save): ItemResponseDTO {
        val savedItem = itemRepository.save(item.toEntity())
        return ItemResponseDTO(savedItem)
    }

}