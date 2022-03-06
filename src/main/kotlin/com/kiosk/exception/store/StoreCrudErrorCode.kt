package com.kiosk.exception.store

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.item.ItemCrudErrorCode
import com.kiosk.exception.order.OrderCrudErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND

enum class StoreCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    STORE_CRUD_FAIL(BAD_REQUEST, -1, "지점 관련 처리 요청이 실패했습니다."),
    STORE_ID_IS_NULL(BAD_REQUEST, -2, "지점 번호가 반드시 전달되어야 합니다."),
    STORE_OWNER_IS_NULL(BAD_REQUEST, -3, "지점 주인 이름 정보는 필수입니다."),
    STORE_NOT_FOUND(NOT_FOUND, -4, "해당 지점은 존재하지 않습니다."),
    STORE_NAME_IS_NULL(BAD_REQUEST, -5, "지점 이름 정보가 반드시 필요합니다."),
    STORE_REVENUE_START_DATE_INVALID(BAD_REQUEST, -6, "지점 매출 집계 시 시작 날짜는 반드시 현재 날짜보다 이전이어야 합니다."),
    STORE_REVENUE_END_DATE_INVALID(BAD_REQUEST, -7, "지점 매출 집계 시 종료 날짜를 반드시 입력해주셔야 합니다.");

    companion object {
        val msgMap = values().associateBy(StoreCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }

        return if (first.isEmpty()) -999 else first[0]
    }
}