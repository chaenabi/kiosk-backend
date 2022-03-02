package com.kiosk.api.item.utils

import com.kiosk.api.item.domain.entity.Item
import com.kiosk.api.item.domain.entity.ItemImage
import com.kiosk.api.item.domain.model.ItemImageDTO
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.function.Consumer
import org.apache.commons.io.FilenameUtils.getBaseName
import org.apache.commons.io.FilenameUtils.getExtension
import org.springframework.beans.factory.annotation.Value

@Component
class ItemImageManager {

    @Value("\${default-upload-image-directory}")
    private var directory: String? = null

    /**
     * 업로드 파일들을 지정된 경로에 모두 저장하고 첨부파일 목록을 반환하여
     * 데이터베이스에도 첨부파일명 등이 저장될 수 있도록 합니다.
     *
     * @param images 업로드된 이미지들
     * @param savedItem 저장된 상품
     * @return 수정된 첨부파일명으로 저장된 결과물
     */
    fun saveImageToDisk(images: List<MultipartFile>, savedItem: Item): MutableList<ItemImage> {
        val files: List<ItemImageDTO> = renamefilename(images, savedItem)
        val result: MutableList<ItemImage> = ArrayList<ItemImage>()
        val folder = File(directory)
        if (!folder.exists()) {
            try {
                Files.createDirectories(Paths.get(directory))
            } catch (e: IOException) {
                throw RuntimeException("업로드 폴더를 생성할 수 없었습니다.", e)
            }
        }
        val attachFileMeasure = images.size
        for (i in 0 until attachFileMeasure) {
            try {
                images[i].transferTo(Paths.get(directory + files[i].name))
            } catch (e: IOException) {
                throw RuntimeException(e.message)
            }
        }
        files.forEach(Consumer<ItemImageDTO> { file: ItemImageDTO -> result.add(file.toEntity()) })
        return result
    }

    /**
     * 같은 파일명이 이미 저장소에 있을 경우 문제가 될 수 있습니다. (파일명 중복시 의도한 파일을 찾지 못하는 문제)
     * 따라서 모든 첨부파일의 이름을 다음과 같이 변경합니다. "DEFAULT_UPLOAD_DIRECTORY첨부파일명_날짜(년월일시간분초)+랜덤문자열.확장자"
     *
     *
     * 예를들어 원본파일명이 example.png 라면 c:/upload/example_20220128171150_bcdc8ebd.png 로 변경됩니다.
     *
     * @param images 업로드된 첨부파일들
     * @param savedItem      외래키 제약조건에 따라 이 상품에 부합하는 첨부파일을 저장하고 식별하기 위한 목적으로 사용
     * @return 수정된 첨부파일명
     */
    private fun renamefilename(images: List<MultipartFile>, savedItem: Item): List<ItemImageDTO> {
        val files: MutableList<ItemImageDTO> = ArrayList<ItemImageDTO>()
        val now = LocalDateTime.now()
        val randomString = UUID.randomUUID().toString().split("-")[0]
        images.forEach(Consumer { file: MultipartFile ->
            files.add(
                ItemImageDTO(
                    name = getBaseName(file.originalFilename)
                            + "_"
                            + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                            + "_"
                            + randomString
                            + "."
                            + getExtension(file.originalFilename),
                    item = savedItem,
                    path = directory
                )
            )
        })
        return files
    }

    fun deleteImageToDisk(images: MutableList<ItemImage>) {
        for (image in images) {
            val file = File("$directory${image.name!!}")
            if (file.exists()) file.delete()
        }
    }
}