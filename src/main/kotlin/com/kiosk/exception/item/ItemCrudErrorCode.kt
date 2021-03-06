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
    ITEM_PAGE_NOT_FOUND(NOT_FOUND, -4, "상품 페이지가 존재하지 않습니다"),
    ITEM_ORDER_COUNT_ZERO_OR_NEGATIVE(BAD_REQUEST, -5, "주문을 하려면 한 개 이상의 상품 갯수 요청이 필요합니다."),
    ITEM_NOT_INSUFFICIENT(BAD_REQUEST, -6, "해당 상품의 남아있는 재고가 주문 요청 수량보다 적게 남아있어 주문 요청을 수행할 수 없었습니다.");

    companion object {
        val msgMap = values().associateBy(ItemCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {
        val first = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }
        return if (first.isEmpty()) -999 else first[0]
    }
}