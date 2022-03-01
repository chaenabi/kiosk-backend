package com.kiosk.api.item.service

import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.model.ItemRequestDTO
import com.kiosk.api.item.domain.model.ItemResponseDTO
import com.kiosk.api.item.repository.ItemImageRepository
import com.kiosk.api.item.repository.ItemRepository
import com.kiosk.api.item.utils.ItemImageManager
import com.kiosk.exception.common.BizException
import com.kiosk.exception.item.ItemCrudErrorCode
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
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

    fun save(item: ItemRequestDTO.Save, images: List<MultipartFile>?): ItemResponseDTO {
        val savedItem = itemRepository.save(item.toEntity())
        val uploadedImages = itemImageManager.saveImageToDisk(images!!, savedItem)
        val savedImages = itemImageRepository.saveAll(uploadedImages)
        return ItemResponseDTO(savedItem, savedImages)
    }

    fun update(item: ItemRequestDTO.Update, images: MutableList<MultipartFile>?): ItemResponseDTO {
        val wantToUpdateItem = findOneEntity(item.id!!)
        wantToUpdateItem.updateItem(item)

        val uploadedImages = itemImageManager.saveImageToDisk(images!!, wantToUpdateItem)
        val savedItemImages = itemImageRepository.findByItemId(wantToUpdateItem.id)
        itemImageRepository.deleteAll(savedItemImages)
        itemImageRepository.flush()
        itemImageRepository.saveAll(uploadedImages)

        return ItemResponseDTO(wantToUpdateItem, uploadedImages)
    }

    fun findItemPage(page: Int, pageSize: Int): ItemResponseDTO.Paging {
        val pageNumber = if (page - 1 >= 0) page - 1 else throw BizException(ItemCrudErrorCode.ITEM_PAGE_NOT_FOUND)

        val pageable: Pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"))
        val responseDTO: ItemResponseDTO.Paging =
            ItemResponseDTO.Paging(pageNumber, itemRepository.findItemAsPage(pageable))

        if (responseDTO.totalPages < (page)) throw BizException(ItemCrudErrorCode.ITEM_PAGE_NOT_FOUND)

        return responseDTO
    }

    fun delete(id: Long) {
        val wantToDeleteItem = findOneEntity(id)
        itemImageManager.deleteImageToDisk(wantToDeleteItem.images)
        itemRepository.delete(wantToDeleteItem)
    }

    fun findOneEntity(id: Long): Item = itemRepository.findById(id)
        .orElseThrow { BizException(ItemCrudErrorCode.ITEM_NOT_FOUND) }

}