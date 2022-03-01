package com.kiosk.exception.item

import com.kiosk.exception.common.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
enum class ItemCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    ITEM_CRUD_FAIL(BAD_REQUEST, -1, "상품 관련 처리 요청이 실패했습니다."),
    ITEM_ID_IS_NULL(BAD_REQUEST, -2, "상품 번호가 반드시 전달되어야 합니다."),
    ITEM_NOT_FOUND(NOT_FOUND, -3, "해당 상품은 존재하지 않습니다."),
    ITEM_PAGE_NOT_FOUND(NOT_FOUND, -4, "상품 페이지가 존재하지 않습니다");

    companion object {
        val msgMap = values().associateBy(ItemCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        println(first)
        return if (first.isEmpty()) -999 else first[0]
    }
}