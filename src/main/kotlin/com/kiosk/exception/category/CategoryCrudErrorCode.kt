package com.kiosk.exception.category

import com.kiosk.exception.common.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
enum class CategoryCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    CATEGORY_CRUD_FAIL(BAD_REQUEST, -1, "카테고리 관련 처리 요청이 실패했습니다."),
    CATEGORY_ID_IS_NULL(BAD_REQUEST, -2, "카테고리 번호가 반드시 전달되어야 합니다."),
    CATEGORY_NOT_FOUND(NOT_FOUND, -3, "해당 카테고리는 존재하지 않습니다."),
    CATEGORY_ADD_ITEM_EMPTY(NOT_FOUND, -4, "아이템 번호가 하나 이상 전달되어야 합니다."),
    CATEGORY_NAME_IS_NULL(NOT_FOUND, -5, "반드시 카테고리명이 전달되어야 합니다.");

    companion object {
        val msgMap = values().associateBy(CategoryCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        return if (first.isEmpty()) -999 else first[0]
    }
}