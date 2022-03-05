package com.kiosk.exception.order

import com.kiosk.exception.common.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND

enum class OrderCrudErrorCode(
    override val httpStatus: HttpStatus,
    override val bizCode: Int,
    override val msg: String
) : ErrorCode {
    ORDER_CRUD_FAIL(BAD_REQUEST, -1, "주문 관련 처리 요청이 실패했습니다."),
    ORDER_ID_IS_NULL(BAD_REQUEST, -2, "주문 번호가 반드시 전달되어야 합니다."),
    ORDER_NOT_FOUND(NOT_FOUND, -3, "해당 주문 번호는 존재하지 않습니다.");

    companion object {
        val msgMap = values().associateBy(OrderCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        println(first)
        return if (first.isEmpty()) -999 else first[0]
    }
}