package com.kiosk.api.item.domain.model

import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.ItemImage
import org.springframework.data.domain.Page
import java.io.File
import java.nio.file.Files
import kotlin.math.ceil

class ItemResponseDTO {

    var id: Long? = null
    lateinit var name: String
    lateinit var detail: String
    var price: Int? = null
    var quantity: Int? = null
    var images: MutableList<ByteArray> = mutableListOf()
    var category: MutableList<String> = mutableListOf()

    private constructor()

    constructor(item: Item, itemImages: MutableList<ItemImage>) {
        this.id = item.id
        this.name = item.name
        this.detail = item.detail
        this.price = item.price
        this.quantity = item.quantity
        itemImages.forEach {
            it.name?.let {
                name -> run {
                    var file = File("${it.path}$name")
                    if (file.exists()) images.add(Files.readAllBytes(file.toPath()))
                }
            }
        }
        item.category.forEach { this.category.add(it.category.name) }
    }

    class Paging(selectedPageNumber: Int, selectedItems: Page<Item>) {
        val selectedPageNumber: Int = ceil((selectedPageNumber + 1) / 10.0).toInt()
        val contents: MutableList<ReProcessing> = ReProcessing.mappingList(selectedItems.content)
        var totalPages: Int = selectedItems.totalPages
    }

    class ReProcessing(
       val id: Long?,
       val itemName: String,
       val detail: String,
       val price: Int,
       val quantity: Int,
       val images: List<ItemImageResponseDTO>
    ) {
        companion object {
            fun mappingList(items: MutableList<Item>): MutableList<ReProcessing> {
                val list: MutableList<ReProcessing> = arrayListOf()

                items.forEach {
                    list.add(
                        ReProcessing(
                        id = it.id,
                        itemName = it.name,
                        detail = it.detail,
                        price = it.price,
                        quantity = it.quantity,
                        images = ItemImageResponseDTO.mapping(it.images)
                    ))
                }
                return list
            }

            fun mapping(item: Item): ReProcessing {
                return ReProcessing(
                    id = item.id,
                    itemName = item.name,
                    detail = item.detail,
                    price = item.price,
                    quantity = item.quantity,
                    images = ItemImageResponseDTO.mapping(item.images)
                )
            }
        }
    }

    class ItemImageResponseDTO(
        val id: Long?,
        val name: String?,
        val bytes: ByteArray
    ) {
        companion object {
            fun mapping(images: MutableList<ItemImage>): MutableList<ItemImageResponseDTO> {
                val list: MutableList<ItemImageResponseDTO> = arrayListOf()

                images.forEach {
                    val file = File("${it.path}${it.name}")
                    println(file.exists())
                    list.add(
                        ItemImageResponseDTO(
                        id = it.id,
                        name = it.name,
                        bytes = (if (file.exists()) Files.readAllBytes(file.toPath()) else byteArrayOf())
                    ))
                }

                return list
            }
        }
    }

}