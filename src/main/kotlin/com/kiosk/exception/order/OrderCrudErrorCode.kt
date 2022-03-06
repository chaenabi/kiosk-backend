package com.kiosk.exception.order

import com.kiosk.exception.common.ErrorCode
import com.kiosk.exception.customer.CustomerCrudErrorCode
import com.kiosk.exception.item.ItemCrudErrorCode
import org.springframework.beans.factory.annotation.Value
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
    ORDER_NOT_FOUND(NOT_FOUND, -3, "해당 주문 번호는 존재하지 않습니다."),
    ORDER_ITEM_COUNT_IS_ZERO_OR_NEGATIVE(BAD_REQUEST, -4, "주문을 하려면 한 개 이상의 상품 갯수 요청이 필요합니다.");

    @Value("\${default-not-matched-biz-code}")
    var notMatched: Int = -999

    companion object {
        val msgMap = values().associateBy(OrderCrudErrorCode::msg)
    }

    override fun findMatchBizCode(failMessage: String?): Int {

        val bizCode = msgMap
            .filter { it.value.msg == failMessage }
            .map { it.value.bizCode }

        var otherBizCode: Int = notMatched

        // itemCrudErrorCode 탐색
        if (bizCode.isEmpty()) otherBizCode = ItemCrudErrorCode.ITEM_CRUD_FAIL.findMatchBizCode(failMessage)

        // storeCrudErrorCode 탐색
        if (bizCode.isEmpty() && otherBizCode == notMatched) otherBizCode =
            CustomerCrudErrorCode.CUSTOMER_CRUD_FAIL.findMatchBizCode(failMessage)

        return if (bizCode.isEmpty() && otherBizCode == notMatched) notMatched
        else if (bizCode.isNotEmpty()) bizCode[0]
        else otherBizCode
    }
}